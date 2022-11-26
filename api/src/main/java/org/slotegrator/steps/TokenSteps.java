package org.slotegrator.steps;

import io.qameta.allure.Step;
import org.slotegrator.enums.EndPoints;
import org.slotegrator.model.request.TokenRequest;
import org.slotegrator.model.response.TokenResponse;

import static io.restassured.RestAssured.given;

public class TokenSteps {

    @Step("Получаем токен для username={username}")
    public TokenResponse getToken(String username, TokenRequest tokenRequest) {
        return given()
                .auth().preemptive().basic(username, "")
                .body(tokenRequest)
                .when()
                .post(EndPoints.TOKEN.getUrl())
                .then()
                .statusCode(200)
                .extract().as(TokenResponse.class);
    }
}
