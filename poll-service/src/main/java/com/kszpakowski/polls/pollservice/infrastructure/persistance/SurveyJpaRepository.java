package com.kszpakowski.polls.pollservice.infrastructure.persistance;

import com.kszpakowski.polls.pollservice.domain.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SurveyJpaRepository extends JpaRepository<Survey, UUID> {
}
