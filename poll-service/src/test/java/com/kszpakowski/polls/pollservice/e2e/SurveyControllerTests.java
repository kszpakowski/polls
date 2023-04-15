package com.kszpakowski.polls.pollservice.e2e;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.kszpakowski.polls.pollservice.infrastructure.api.survey.AddQuestionsRequest;
import com.kszpakowski.polls.pollservice.infrastructure.api.survey.CreateSurveyRequest;
import io.restassured.http.ContentType;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerTests {

  @LocalServerPort private int port;

  @Test
  public void shouldCreateASurvey() {
    String surveyId =
        given()
            .port(port)
            .with()
            .body(new CreateSurveyRequest("Test survey"))
            .contentType(ContentType.JSON)
            .when()
            .post("/v1/surveys")
            .then()
            .statusCode(201)
            .body("title", equalTo("Test survey"))
            .extract()
            .jsonPath()
            .getString("id");

    // add open question
    given()
        .port(port)
        .with()
        .body(
            new AddQuestionsRequest(
                "OPEN",
                "Jakie czynniki wpływają na Twoją decyzję o pozostaniu zalogowanym na portalu ubezpieczeniowym przez dłuższy czas?",
                null))
        .contentType(ContentType.JSON)
        .when()
        .post("/v1/surveys/" + surveyId + "/questions")
        .then()
        .statusCode(200);

    // add single choice question
    given()
        .port(port)
        .with()
        .body(
            new AddQuestionsRequest(
                "SINGLE_CHOICE",
                "Jak oceniasz proces logowania do portalu ubezpieczeniowego?",
                Set.of("Bardzo łatwy", "Łatwy", "Średnio trudny", "Trudny", "Bardzo trudny")))
        .contentType(ContentType.JSON)
        .when()
        .post("/v1/surveys/" + surveyId + "/questions")
        .then()
        .statusCode(200);
  }
}
