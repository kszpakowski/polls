package com.kszpakowski.polls.pollservice.infrastructure.persistance;

import com.kszpakowski.polls.pollservice.domain.survey.Survey;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyJpaRepository extends JpaRepository<Survey, UUID> {}
