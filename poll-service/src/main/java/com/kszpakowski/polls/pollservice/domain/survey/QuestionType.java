package com.kszpakowski.polls.pollservice.domain.survey;

public enum QuestionType {
  SINGLE_CHOICE("Question with single choice answer"),
  MULTI_CHOICE("Question with multiple choice answer"),
  OPEN("Question with text answer"),
  SCALE("Question with rating scale answer");

  private final String description;

  QuestionType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
