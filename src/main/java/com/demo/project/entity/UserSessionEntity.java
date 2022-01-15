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
@Table(name = "user_session")
@Entity(name = "user_session")
public class UserSessionEntity extends AbstractJpaEntity {

	private static final long serialVersionUID = -947751731338229446L;

	@Column(name = "token", columnDefinition = "varchar(100)", unique = true, nullable = false)
	private String token;

	@Column(name = "mobile_no", columnDefinition = "varchar(15)")
	private String mobileNo;

}