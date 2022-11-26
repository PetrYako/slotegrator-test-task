import config.RestAssuredExtension;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slotegrator.config.Properties;
import org.slotegrator.enums.GrantType;
import org.slotegrator.enums.Scope;
import org.slotegrator.model.request.TokenRequest;
import org.slotegrator.model.response.TokenResponse;
import org.slotegrator.steps.TokenSteps;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(RestAssuredExtension.class)
@Feature("Токен")
public class GetGuestTokenTest {

    private final TokenSteps tokenSteps = new TokenSteps();
    private final Properties properties = RestAssuredExtension.properties;

    @Test
    @DisplayName("Получить токен гостя (Client Credentials Grant, scope — guest:default)")
    void testGetGuestToken() {
        String username = properties.guestUsername();

        TokenRequest tokenReq = TokenRequest.builder()
                .grantType(GrantType.CLIENT_CREDENTIALS.toString())
                .scope(Scope.GUEST.getDefaultScope())
                .build();

        TokenResponse tokenResp = tokenSteps.getToken(username, tokenReq);

        assertNotNull(tokenResp.getAccessToken(), "В ответе отсутсвует поле access_token");
    }
}
