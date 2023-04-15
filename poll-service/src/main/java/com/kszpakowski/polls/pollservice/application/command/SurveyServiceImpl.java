package com.kszpakowski.polls.pollservice.application.command;

import com.kszpakowski.polls.pollservice.domain.survey.Survey;

import com.kszpakowski.polls.pollservice.infrastructure.persistance.SurveyJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
class SurveyServiceImpl implements SurveyService{

    private final SurveyJpaRepository surveyJpaRepository;

    public SurveyServiceImpl(SurveyJpaRepository surveyJpaRepository) {
        this.surveyJpaRepository = surveyJpaRepository;
    }

    @Override
    public Survey createSurvey(CreateSurveyCommand cmd) {
        var survey = Survey.withTitle(cmd.title());
        return surveyJpaRepository.save(survey);
    }

    @Override
    public Survey addQuestion(AddQuestionCommand cmd) {
        return null;
    }
}
