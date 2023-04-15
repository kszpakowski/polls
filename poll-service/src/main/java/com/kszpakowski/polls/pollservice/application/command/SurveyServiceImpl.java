package com.kszpakowski.polls.pollservice.application.command;

import com.kszpakowski.polls.pollservice.domain.survey.Question;
import com.kszpakowski.polls.pollservice.domain.survey.QuestionType;
import com.kszpakowski.polls.pollservice.domain.survey.Survey;

import com.kszpakowski.polls.pollservice.domain.survey.exception.SurveyNotFoundException;
import com.kszpakowski.polls.pollservice.infrastructure.persistance.SurveyJpaRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        if(cmd.type() == QuestionType.OPEN){
            var surveyOptional = surveyJpaRepository.findById(UUID.fromString(cmd.surveyId()));
            var question = Question.openQuestion(cmd.questionText());
            return surveyOptional.map(s -> s.addQuestion(question))
                    .map(surveyJpaRepository::save)
                    .orElseThrow(SurveyNotFoundException::new);
        }else {
            throw new NotImplementedException();
        }
    }
}
