import config.RestAssuredExtension;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.aeonbits.owner.util.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slotegrator.config.Properties;
import org.slotegrator.enums.GrantType;
import org.slotegrator.helpers.PlayersHelper;
import org.slotegrator.model.request.TokenRequest;
import org.slotegrator.model.response.PlayersResponse;
import org.slotegrator.model.response.TokenResponse;
import org.slotegrator.steps.PlayersSteps;
import org.slotegrator.steps.TokenSteps;

@ExtendWith(RestAssuredExtension.class)
@Feature("Запрос данных")
public class GetAnotherPlayerDataTest {

    private final TokenSteps tokenSteps = new TokenSteps();
    private final PlayersHelper playersHelper = new PlayersHelper();
    private final PlayersSteps playersSteps = new PlayersSteps();
    private final Properties properties = RestAssuredExtension.properties;

    private PlayersResponse createPlayer() {
        String newUsername = "AT-" + RandomUtils.nextLong();
        String password = Base64.encode(RandomStringUtils.randomAlphanumeric(8).getBytes());

        return playersHelper.registerNewPlayer(newUsername, password);
    }

    @Test
    @DisplayName("Запросить данные другого игрока")
    void testGetAnotherPlayerDataTest() {
        String newUsername = "AT-" + RandomUtils.nextLong();
        String password = Base64.encode(RandomStringUtils.randomAlphanumeric(8).getBytes());

        playersHelper.registerNewPlayer(newUsername, password);
        PlayersResponse secondPlayerResp = createPlayer();

        TokenRequest tokenReq = TokenRequest.builder()
                .grantType(GrantType.PASSWORD.toString())
                .username(newUsername)
                .password(password)
                .build();

        TokenResponse firstPlayerTokenResp = tokenSteps.getToken(properties.guestUsername(), tokenReq);
        Response playerDataResp = playersSteps.getPlayerData404(secondPlayerResp.getId(), firstPlayerTokenResp.getAccessToken());

        Assertions.assertEquals(404, playerDataResp.getStatusCode());
    }
}
