package com.kszpakowski.polls.pollservice.application.command;

import com.kszpakowski.polls.pollservice.domain.survey.Survey;

public interface SurveyService {

  Survey createSurvey(CreateSurveyCommand cmd);

  Survey addQuestion(AddQuestionCommand cmd);
}
