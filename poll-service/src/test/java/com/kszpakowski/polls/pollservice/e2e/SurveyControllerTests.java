package com.kszpakowski.polls.pollservice.e2e;

import com.kszpakowski.polls.pollservice.infrastructure.api.survey.CreateSurveyRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerTests {

    @LocalServerPort
    private int port;

    @Test
    public void shouldCreateASurvey() {
        given()
                .port(port)
                .with()
                .body(new CreateSurveyRequest("Test survey"))
                .contentType(ContentType.JSON)
                .when()
                .post("/v1/surveys")
                .then()
                .statusCode(201)
                .body("questionText", equalTo("Test survey"));
    }
}

