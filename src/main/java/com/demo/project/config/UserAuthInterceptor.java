package com.demo.project.config;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.security.auth.message.AuthException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demo.project.entity.UserSessionEntity;
import com.demo.project.service.SessionService;
import com.demo.project.utils.SecurityConstants;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class UserAuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private SessionService sessionService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String token = extractTokenFromRequest(request);
		try {
			if (StringUtils.isNotBlank(token)) {
				UserSessionEntity userSession = sessionService.getUserSessionByToken(token);

				if (userSession != null && userSession.isStatus()) {
					boolean isSessionExpired = isSessionExpired(userSession);
					markInactiveIfSessionExpired(isSessionExpired, userSession);
					setUpdatedAtAndSave(userSession);
					sessionExpirationCheck(isSessionExpired, request, response);
					request.setAttribute(SecurityConstants.MobileNo, userSession.getMobileNo());
					return true;
				} else {
					log.error("No User Session found with Token: " + token + ". Cann't authorize user.");
				}
			} else {
				log.error("User Token is null/empty. Can't authorize user. Send to login for url :"
						+ request.getRequestURI());
			}
		} catch (Exception e) {
			log.error("Error validating user token: ", e);
		}

		response.setStatus(HttpStatus.UNAUTHORIZED.value());

		return false;
	}

	private String extractTokenFromRequest(HttpServletRequest request) {
		String token = null;

		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (SecurityConstants.TOKEN_HEADER_NAME.equals(cookie.getName())) {
					token = cookie.getValue();
					break;
				}
			}
		}
		return token;
	}

	private boolean isSessionExpired(UserSessionEntity userSession) {
		boolean isSessionExpired = false;
		int inActivityMinutes = SecurityConstants.SESSION_EXPIRED;

		if (inActivityMinutes > 0) {
			Date expiryTime = getUserExpiryTime(inActivityMinutes);

			if (userSession.getUpdatedAt() == null || userSession.getUpdatedAt().before(expiryTime)) {
				isSessionExpired = true;
			}
		}
		log.debug("UserId: {} Session Expiration Status: {}", userSession.getMobileNo(), isSessionExpired);

		return isSessionExpired;
	}

	private Date getUserExpiryTime(int inactivityMinutes) {
		ZoneId zone = ZoneId.of("Asia/Kolkata");

		return new Date(LocalDateTime.now(zone).atZone(zone).toInstant().toEpochMilli() - (inactivityMinutes * 60000));
	}

	private void markInactiveIfSessionExpired(boolean isSessionExpired, UserSessionEntity userSession) {
		if (isSessionExpired) {
			userSession.setStatus(false);
		}
	}

	private void setUpdatedAtAndSave(UserSessionEntity userSession) {
		userSession.setUpdatedAt(new Date());
		sessionService.updateUserSession(userSession);
	}

	private void sessionExpirationCheck(boolean isSessionExpired, HttpServletRequest request,
			HttpServletResponse response) throws AuthException {
		if (isSessionExpired) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			throw new AuthException("User Session has expired");
		}
	}
}