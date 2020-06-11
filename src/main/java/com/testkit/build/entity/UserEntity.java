package com.testkit.build.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.testkit.build.enums.UserType;

import lombok.Data;

@Entity(name = "appuser")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
@Data
public abstract class UserEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_password")
	private String userPassword;

	@Column(name = "user_mobile")
	private String userMobile;

	@Column(name = "user_type", insertable = false, updatable = false)
	private UserType userType;
}
