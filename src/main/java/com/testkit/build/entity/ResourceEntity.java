package com.testkit.build.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "resource")
@DiscriminatorValue("2")
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceEntity extends UserEntity {

	@Column(name = "registration_date")
	@Temporal(TemporalType.DATE)
	private Date registrationDate;
}
