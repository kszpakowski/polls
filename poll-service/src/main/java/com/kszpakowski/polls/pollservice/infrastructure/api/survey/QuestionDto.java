package com.kszpakowski.polls.pollservice.infrastructure.api.survey;

import java.util.Set;

public record QuestionDto(String id, String type, String questionText, Set<ChoiceDto> choices) {}
