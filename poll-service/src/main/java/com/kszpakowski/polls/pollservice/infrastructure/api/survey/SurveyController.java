package com.kszpakowski.polls.pollservice.infrastructure.api.survey;

import com.kszpakowski.polls.pollservice.application.command.AddQuestionCommand;
import com.kszpakowski.polls.pollservice.application.command.CreateSurveyCommand;
import com.kszpakowski.polls.pollservice.application.command.SurveyService;
import com.kszpakowski.polls.pollservice.application.query.SingleSurveyQuery;
import com.kszpakowski.polls.pollservice.application.query.SurveyRepository;
import com.kszpakowski.polls.pollservice.domain.survey.QuestionType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/surveys")
public class SurveyController {

    private final SurveyService surveyService;
    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;

    public SurveyController(SurveyService surveyService, SurveyRepository surveyRepository, SurveyMapper surveyMapper) {
        this.surveyService = surveyService;
        this.surveyRepository = surveyRepository;
        this.surveyMapper = surveyMapper;
    }

    @PostMapping
    public ResponseEntity<SurveyDto> createSurvey(@RequestBody CreateSurveyRequest req) {
        var cmd = new CreateSurveyCommand(req.title());
        var survey = surveyService.createSurvey(cmd);
        SurveyDto dto = surveyMapper.toDto(survey);
        return ResponseEntity.created(URI.create("/v1/surveys/" + survey.getId())).body(dto);
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyDto> getSurvey(@PathVariable String surveyId) {
        var query = new SingleSurveyQuery(surveyId);
        var dtoOptional = surveyRepository.getSurvey(query).map(surveyMapper::toDto);
        return dtoOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //TODO implement adding multiple questions at once
    @PostMapping("/{surveyId}/questions")
    public ResponseEntity<SurveyDto> addSurveyQuestion(@PathVariable String surveyId, @RequestBody AddQuestionsRequest req) {
        AddQuestionCommand cmd = new AddQuestionCommand(surveyId, QuestionType.valueOf(req.type()), req.questionText());
        return ResponseEntity.ok(surveyMapper.toDto(surveyService.addQuestion(cmd)));
    }

}
