package com.kszpakowski.polls.pollservice.application.command;

import com.kszpakowski.polls.pollservice.domain.survey.Question;
import com.kszpakowski.polls.pollservice.domain.survey.QuestionType;
import com.kszpakowski.polls.pollservice.domain.survey.Survey;
import com.kszpakowski.polls.pollservice.domain.survey.exception.SurveyNotFoundException;
import com.kszpakowski.polls.pollservice.infrastructure.persistance.SurveyJpaRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

@Service
@Transactional
class SurveyServiceImpl implements SurveyService {

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
    var surveyOptional = surveyJpaRepository.findById(UUID.fromString(cmd.surveyId()));
    var question = buildQuestion(cmd);
    return surveyOptional
        .map(s -> s.addQuestion(question))
        .map(surveyJpaRepository::save)
        .orElseThrow(SurveyNotFoundException::new);
  }

  private Question buildQuestion(AddQuestionCommand cmd) {
    if (cmd.type() == QuestionType.OPEN) {
      return Question.openQuestion(cmd.questionText());
    } else if (cmd.type() == QuestionType.SINGLE_CHOICE) {
      return Question.singleChoice(cmd.questionText(), cmd.choices());
    } else {
      throw new NotImplementedException();
    }
  }
}
