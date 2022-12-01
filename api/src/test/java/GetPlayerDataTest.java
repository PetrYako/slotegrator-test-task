import config.RestAssuredExtension;
import io.qameta.allure.Feature;
import org.aeonbits.owner.util.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(RestAssuredExtension.class)
@Feature("Запрос данных")
public class GetPlayerDataTest {

    private final TokenSteps tokenSteps = new TokenSteps();
    private final PlayersHelper playersHelper = new PlayersHelper();
    private final PlayersSteps playersSteps = new PlayersSteps();
    private final Properties properties = RestAssuredExtension.properties;

    @Test
    @DisplayName("Запросить данные профиля игрока")
    void testGetPlayerData() {
        String newUsername = "AT-" + RandomUtils.nextLong();
        String password = Base64.encode(RandomStringUtils.randomAlphanumeric(8).getBytes());

        PlayersResponse registrationResp = playersHelper.registerNewPlayerWith(newUsername, password);

        TokenRequest tokenReq = TokenRequest.builder()
                .grantType(GrantType.PASSWORD.toString())
                .username(newUsername)
                .password(password)
                .build();

        TokenResponse tokenResp = tokenSteps.getToken(properties.guestUsername(), tokenReq);
        PlayersResponse playerResp = playersSteps.getPlayerData(registrationResp.getId(), tokenResp.getAccessToken());

        assertEquals(registrationResp, playerResp);
    }
}
