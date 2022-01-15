package com.demo.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.entity.UserSessionEntity;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSessionEntity, Integer> {

	UserSessionEntity findByMobileNo(String mobileNo);

	UserSessionEntity findByToken(String token);

}
