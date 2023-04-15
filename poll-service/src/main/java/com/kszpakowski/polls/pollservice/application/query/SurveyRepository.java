package com.kszpakowski.polls.pollservice.application.query;

import com.kszpakowski.polls.pollservice.domain.survey.Survey;

import java.util.Optional;

public interface SurveyRepository {

    Optional<Survey> getSurvey(SingleSurveyQuery query);
}
