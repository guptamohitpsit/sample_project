package com.demo.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.demo.project.Enum.PaymentMode;
import com.demo.project.Enum.PaymentStatus;
import com.demo.project.Enum.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
@Entity(name = "transaction")
public class TransactionEntity extends AbstractJpaEntity {

	private static final long serialVersionUID = 2429210304319495082L;

	@Column(name = "amount", nullable = false, columnDefinition = "DOUBLE NOT NULL")
	private double amount;

	@Column(name = "mobile_no", columnDefinition = "varchar(15)")
	private String mobileNo;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_mode", nullable = false)
	private PaymentMode paymentMode;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status", nullable = false)
	private PaymentStatus paymentStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type", nullable = false)
	private TransactionType transactionType;

}
