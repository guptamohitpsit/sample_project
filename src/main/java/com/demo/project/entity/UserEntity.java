package com.demo.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.demo.project.Enum.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "user")
@Entity(name = "user")
public class UserEntity extends AbstractJpaEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "first_name", columnDefinition = "varchar(100)", nullable = false)
	private String firstName;

	@Column(name = "middle_name", columnDefinition = "varchar(100)")
	private String middleName;

	@Column(name = "last_name", columnDefinition = "varchar(100)")
	private String lastName;

	@Column(name = "mobile_no", columnDefinition = "varchar(15)")
	private String mobileNo;

	@Column(name = "email_id", columnDefinition = "varchar(100)")
	private String emailId;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", columnDefinition = "varchar(20)")
	private Gender gender;
	
	@Column(name = "wallet", columnDefinition = "Double",nullable = false)
	@Builder.Default
	private Double wallet=0d;
	
	
	@Column(name = "address_line_1", columnDefinition = "TEXT")
	private String addressLine1;

	@Column(name = "address_line_2", columnDefinition = "TEXT")
	private String addressLine2;

}
