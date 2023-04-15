package com.kszpakowski.polls.pollservice.application.command;


import com.kszpakowski.polls.pollservice.domain.survey.QuestionType;

public record AddQuestionCommand(String surveyId, QuestionType type, String questionText) {
}
