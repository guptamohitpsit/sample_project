package com.demo.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private UserAuthInterceptor userAuthInterceptor;

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(userAuthInterceptor).addPathPatterns("/**")
		.excludePathPatterns(
				"/login/**",
				 "/resendOtp",
				 "/validateOtp",
				 "/swagger-ui.html/**",
				 "/configuration/ui/",
                 "/swagger-resources/**",
                 "/configuration/security",
                 "/webjars/**"
                 
				);

	}
}