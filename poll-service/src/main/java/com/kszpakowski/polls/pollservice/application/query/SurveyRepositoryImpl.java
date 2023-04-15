package com.kszpakowski.polls.pollservice.application.query;

import com.kszpakowski.polls.pollservice.domain.survey.Survey;
import com.kszpakowski.polls.pollservice.infrastructure.persistance.SurveyJpaRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
class SurveyRepositoryImpl implements SurveyRepository {

  private final SurveyJpaRepository repository;

  SurveyRepositoryImpl(SurveyJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<Survey> getSurvey(SingleSurveyQuery query) {
    UUID id = UUID.fromString(query.surveyId());
    return repository.findById(id);
  }
}
