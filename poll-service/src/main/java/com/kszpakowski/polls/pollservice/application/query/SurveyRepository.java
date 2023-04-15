package com.kszpakowski.polls.pollservice.application.query;

import com.kszpakowski.polls.pollservice.domain.survey.Question;
import com.kszpakowski.polls.pollservice.domain.survey.Survey;

import java.util.Optional;
import java.util.Set;

public interface SurveyRepository {

    Optional<Survey> getSurvey(SingleSurveyQuery query);
    Set<Question> getQuestions(SingleSurveyQuery query);
}
