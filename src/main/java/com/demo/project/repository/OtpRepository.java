package com.demo.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.entity.OtpEntity;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, Integer> {

	OtpEntity findByMobileNo(String mobileNo);

	OtpEntity findByMobileNoAndStatus(String mobileNo, Boolean true1);

}
