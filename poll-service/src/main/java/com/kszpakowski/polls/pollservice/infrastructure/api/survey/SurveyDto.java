package com.kszpakowski.polls.pollservice.infrastructure.api.survey;

import java.util.Set;

public record SurveyDto(String id, String title, Set<QuestionDto> questions) {}
