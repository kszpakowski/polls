package com.kszpakowski.polls.pollservice.infrastructure.api.survey;

import com.kszpakowski.polls.pollservice.domain.survey.Choice;
import com.kszpakowski.polls.pollservice.domain.survey.Question;
import com.kszpakowski.polls.pollservice.domain.survey.Survey;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SurveyMapper {
  public SurveyDto toDto(Survey survey) {
    var questions = questionsToDto(survey.getQuestions());
    return new SurveyDto(survey.getId(), survey.getTitle(), questions);
  }

  public QuestionDto toDto(Question question) {
    var choices = choicesToDto(question.getChoices());
    return new QuestionDto(
        question.getId(), question.getType().toString(), question.getQuestionText(), choices);
  }

  public Set<QuestionDto> questionsToDto(Set<Question> questions) {
    return questions.stream().map(this::toDto).collect(Collectors.toSet());
  }

  public ChoiceDto toDto(Choice choice) {
    return new ChoiceDto(choice.getId(), choice.getText());
  }

  public Set<ChoiceDto> choicesToDto(Set<Choice> choices) {
    return choices.stream().map(this::toDto).collect(Collectors.toSet());
  }
}
