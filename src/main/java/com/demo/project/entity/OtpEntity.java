
package com.demo.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "otp")
@Entity(name = "otp")
public class OtpEntity extends AbstractJpaEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "otp", columnDefinition = "int(8)", nullable = false)
	private Integer otp;

	@Column(name = "mobile_no", columnDefinition = "varchar(15)")
	private String mobileNo;

	@Column(name = "resend_count", columnDefinition = "int(2)", nullable = false)
	private int resendCount;

	@Column(name = "validate_count", columnDefinition = "int(2)", nullable = false)
	private int validateCount;

}