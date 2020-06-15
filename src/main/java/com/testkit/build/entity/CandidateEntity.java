package com.testkit.build.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "candidate")
@DiscriminatorValue("1")
@Data
@EqualsAndHashCode(callSuper = false)
public class CandidateEntity extends UserEntity {

	@Column(name = "registration_date")
	@Size(max = 10)
	@Temporal(TemporalType.DATE)
	private Date registrationDate;
}
