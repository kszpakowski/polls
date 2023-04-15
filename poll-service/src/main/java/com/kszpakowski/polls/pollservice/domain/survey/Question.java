package com.kszpakowski.polls.pollservice.domain.survey;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "question")
public class Question {

  @Id @GeneratedValue private UUID id;

  @Column(name = "question_id")
  private UUID surveyId;

  @Enumerated(EnumType.STRING)
  @Column(name = "question_type", nullable = false)
  private QuestionType questionType;

  @Column(name = "question_text")
  private String questionText;

  @Column(name = "position")
  private int position;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Survey survey;

  // TODO add choices relation

  public static Question openQuestion(String questionText) {
    var question = new Question();
    question.questionType = QuestionType.OPEN;
    question.questionText = questionText;
    return question;
  }

  public Survey getSurvey() {
    return survey;
  }

  public void setSurvey(Survey surveys) {
    this.survey = surveys;
  }

  public QuestionType getType() {
    return this.questionType;
  }

  public String getQuestionText() {
    return this.questionText;
  }

  public String getId() {
    return this.id.toString();
  }
}
