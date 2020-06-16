package com.testkit.build.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "admin")
@Data
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("0")
public class AdminEntity extends UserEntity {

	@Column(name = "admin_level")
	@Size(max = 2)
	private String adminLevel;

}
