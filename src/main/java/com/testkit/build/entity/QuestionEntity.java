package com.testkit.build.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "question_text")
	@Size(max = 256)
	private String questionText;

	@Column(name = "time")
	private int time;

	@Column(name = "type")
	@Size(max = 20)
	private String type;

	@Column(name = "answer")
	@Size(max = 256)
	private String answer;

	@OneToMany(mappedBy = "questionEntity")
	List<OptionEntity> optionEntityList;
}
