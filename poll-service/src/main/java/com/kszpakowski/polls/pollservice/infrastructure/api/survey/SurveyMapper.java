package com.kszpakowski.polls.pollservice.infrastructure.api.survey;


import com.kszpakowski.polls.pollservice.domain.survey.Question;
import com.kszpakowski.polls.pollservice.domain.survey.Survey;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SurveyMapper {
    public SurveyDto toDto(Survey survey) {
        var questions = manyToDto(survey.getQuestions());
        return new SurveyDto(survey.getId(), survey.getTitle(), questions);
    }

    public QuestionDto toDto(Question question) {
        return new QuestionDto(question.getId(), question.getType().toString(), question.getQuestionText());
    }

    public Set<QuestionDto> manyToDto(Set<Question> questions) {
        return questions.stream().map(this::toDto).collect(Collectors.toSet());
    }
}
