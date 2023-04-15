package com.kszpakowski.polls.pollservice.infrastructure.api.survey;

import java.util.Set;

public record AddQuestionsRequest(String type, String questionText, Set<String> choices) {}
