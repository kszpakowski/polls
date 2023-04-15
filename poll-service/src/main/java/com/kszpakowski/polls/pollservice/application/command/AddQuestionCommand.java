package com.kszpakowski.polls.pollservice.application.command;

import com.kszpakowski.polls.pollservice.domain.survey.QuestionType;
import java.util.Set;

public record AddQuestionCommand(
    String surveyId, QuestionType type, String questionText, Set<String> choices) {}
