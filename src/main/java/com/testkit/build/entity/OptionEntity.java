package com.testkit.build.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "option")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "option_text")
	@Size(max = 256)
	private String optionText;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private QuestionEntity questionEntity;

	@Column(name = "question_id", updatable = false, insertable = false)
	private int questionId;

}
