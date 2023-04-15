package com.kszpakowski.polls.pollservice.domain.survey;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "survey")
public class Survey {

  @Id @GeneratedValue private UUID id;

  //    @NotBlank
  @Column(name = "questionText")
  private String title;

  @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Question> questions = new HashSet<>();

  public static Survey withTitle(String title) {
    var survey = new Survey();
    survey.title = title;
    return survey;
  }

  public Survey addQuestion(Question question) {
    question.setSurvey(this);
    this.questions.add(question);
    return this;
  }

  public String getId() {
    return id.toString();
  }

  public String getTitle() {
    return title;
  }

  public Set<Question> getQuestions() {
    return questions;
  }
}
