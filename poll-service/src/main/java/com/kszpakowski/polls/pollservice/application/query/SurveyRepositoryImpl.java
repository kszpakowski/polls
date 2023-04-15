package com.kszpakowski.polls.pollservice.application.query;

import com.kszpakowski.polls.pollservice.domain.survey.Question;
import com.kszpakowski.polls.pollservice.domain.survey.Survey;
import com.kszpakowski.polls.pollservice.domain.survey.exception.SurveyNotFoundException;
import com.kszpakowski.polls.pollservice.infrastructure.persistance.SurveyJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
class SurveyRepositoryImpl implements SurveyRepository{

    private final SurveyJpaRepository repository;

    SurveyRepositoryImpl(SurveyJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Survey> getSurvey(SingleSurveyQuery query) {
        UUID id = UUID.fromString(query.surveyId());
        return repository.findById(id);
    }

    @Override
    public Set<Question> getQuestions(SingleSurveyQuery query) {
        UUID id = UUID.fromString(query.surveyId());
        return repository.findById(id)
                .map(Survey::getQuestions)
                .orElseThrow(SurveyNotFoundException::new);
    }
}
