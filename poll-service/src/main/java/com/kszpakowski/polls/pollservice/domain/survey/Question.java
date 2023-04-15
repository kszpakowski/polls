package com.kszpakowski.polls.pollservice.domain.survey;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.apache.commons.collections4.set.UnmodifiableSet;

@Entity
@Table(name = "question")
public class Question {

  @Id @GeneratedValue private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(name = "question_type", nullable = false)
  private QuestionType questionType;

  @Column(name = "question_text")
  private String questionText;

  @Column(name = "position")
  private int position;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Survey survey;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Choice> choices = new HashSet<>();

  public static Question openQuestion(String questionText) {
    var question = new Question();
    question.questionType = QuestionType.OPEN;
    question.questionText = questionText;
    return question;
  }

  public static Question singleChoice(String questionText, Set<String> choices) {
    if (choices == null || choices.size() < 2) {
      throw new RuntimeException("Single choice question must have at least two choices");
    }
    var question = new Question();
    question.questionType = QuestionType.SINGLE_CHOICE;
    question.questionText = questionText;
    choices.stream()
        .map(choice -> Choice.withText(question, choice))
        .forEach(question.choices::add);

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

  public Set<Choice> getChoices() {
    return UnmodifiableSet.unmodifiableSet(this.choices);
  }
}
