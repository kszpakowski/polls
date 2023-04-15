package com.kszpakowski.polls.pollservice.infrastructure.api.survey;


import com.kszpakowski.polls.pollservice.domain.survey.Survey;
import org.springframework.stereotype.Component;

@Component
public class SurveyMapper {
    public SurveyDto toDto(Survey survey) {
        return new SurveyDto(survey.getId(), survey.getTitle());
    }
}
