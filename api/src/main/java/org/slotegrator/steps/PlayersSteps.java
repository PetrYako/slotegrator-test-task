package org.slotegrator.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.slotegrator.enums.EndPoints;
import org.slotegrator.model.request.PlayersRequest;
import org.slotegrator.model.response.PlayersResponse;

import static io.restassured.RestAssured.given;

public class PlayersSteps {

    @Step("Регистрация пользователя с username={playersRequest.username}")
    public PlayersResponse registerPlayer(PlayersRequest playersRequest, String accessToken) {
        return given()
                .auth().preemptive().oauth2(accessToken)
                .body(playersRequest)
                .when()
                .post(EndPoints.PLAYERS.getUrl())
                .then()
                .statusCode(201)
                .extract().as(PlayersResponse.class);
    }

    @Step("Полученние данных пользователя с id={id}")
    public PlayersResponse getPlayerData(long id, String accessToken) {
        return given()
                .auth().preemptive().oauth2(accessToken)
                .when()
                .get(EndPoints.PLAYERS.getUrl() + "/" + id)
                .then()
                .statusCode(200)
                .extract().as(PlayersResponse.class);
    }

    @Step("Полученние данных другого пользователя с id={id}")
    public Response getPlayerData404(long id, String accessToken) {
        return given()
                .auth().preemptive().oauth2(accessToken)
                .when()
                .get(EndPoints.PLAYERS.getUrl() + "/" + id)
                .then()
                .extract().response();
    }
}
