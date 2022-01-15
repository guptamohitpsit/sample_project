package com.demo.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.Enum.TransactionType;
import com.demo.project.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

	List<TransactionEntity> findByMobileNoAndTransactionType(String mobileNo, TransactionType transactionType);

	List<TransactionEntity> findByMobileNo(String mobileNo);

}
