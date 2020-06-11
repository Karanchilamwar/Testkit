package com.testkit.build.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "candidate")
@DiscriminatorValue("1")
@Data
@EqualsAndHashCode(callSuper = false)
public class CandidateEntity extends UserEntity {

	@Column(name = "registration_date")
	private Date registrationDate;
}
