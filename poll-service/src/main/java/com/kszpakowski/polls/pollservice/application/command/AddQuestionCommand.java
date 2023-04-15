package com.kszpakowski.polls.pollservice.application.command;

import com.kszpakowski.polls.pollservice.domain.survey.Survey;

public record AddQuestionCommand(Survey survey, String title) {
}
