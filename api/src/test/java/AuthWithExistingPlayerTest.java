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
import org.slotegrator.model.response.TokenResponse;
import org.slotegrator.steps.TokenSteps;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(RestAssuredExtension.class)
@Feature("Авторизация")
public class AuthWithExistingPlayerTest {

    private final TokenSteps tokenSteps = new TokenSteps();
    private final PlayersHelper playersHelper = new PlayersHelper();
    private final Properties properties = RestAssuredExtension.properties;

    @Test
    @DisplayName("Авторизоваться под созданным игроком (Resource Owner Password Credentials Grant)")
    void testAuthWithExistingPlayer() {
        String newUsername = "AT-" + RandomUtils.nextLong();
        String password = Base64.encode(RandomStringUtils.randomAlphanumeric(8).getBytes());

        playersHelper.registerNewPlayerWith(newUsername, password);

        TokenRequest tokenReq = TokenRequest.builder()
                .grantType(GrantType.PASSWORD.toString())
                .username(newUsername)
                .password(password)
                .build();

        TokenResponse tokenResp = tokenSteps.getToken(properties.guestUsername(), tokenReq);

        assertNotNull(tokenResp.getAccessToken(), "Отсутствует access_token");
    }

}
