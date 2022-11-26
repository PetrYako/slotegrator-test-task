import com.github.javafaker.Faker;
import config.RestAssuredExtension;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.aeonbits.owner.util.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slotegrator.builders.PlayersBuilder;
import org.slotegrator.config.Properties;
import org.slotegrator.enums.Currency;
import org.slotegrator.helpers.TokenHelper;
import org.slotegrator.model.request.PlayersRequest;
import org.slotegrator.model.response.PlayersResponse;
import org.slotegrator.steps.PlayersSteps;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RestAssuredExtension.class)
@Feature("Регистрация")
public class PlayerRegistrationTest {

    private final TokenHelper tokenHelper = new TokenHelper();
    private final PlayersSteps playersSteps = new PlayersSteps();
    private final PlayersBuilder playersBuilder = new PlayersBuilder();
    private final Faker faker = new Faker();
    private final Properties properties = RestAssuredExtension.properties;

    @Test
    @DisplayName("Зарегистрировать игрока")
    @Issue("Тест не проходит из-за передачи currency_code")
    void testPlayerRegistration() {
        String guestUsername = properties.guestUsername();
        String newUsername = "AT-" + RandomUtils.nextLong();
        String password = Base64.encode(RandomStringUtils.randomAlphanumeric(8).getBytes());
        String email = newUsername + "@example.com";
        String name = faker.name().firstName();
        String surname = faker.name().lastName();

        PlayersRequest registrationReq = playersBuilder.createPlayerForRegistration(newUsername, password, email, name, surname, Currency.EUR.name());
        String accessToken = tokenHelper.getGuestToken(guestUsername);
        PlayersResponse registrationResp = playersSteps.registerPlayer(registrationReq, accessToken);

        assertNotNull(registrationResp.getId(), "Значение id не может быть null");
        assertNull(registrationResp.getCountryId(), "Значение country_id должно быть null");
        assertNull(registrationResp.getTimezoneId(), "Значение timezone_id должно быть null");
        assertEquals(newUsername, registrationResp.getUsername(), "username не совпадает");
        assertEquals(email, registrationResp.getEmail(), "email не совпадает");
        assertEquals(name, registrationResp.getName(), "name не совпадает");
        assertEquals(surname, registrationResp.getSurname(), "surname не совпадает");
        assertNull(registrationResp.getGender(), "gender должен быть null");
        assertNull(registrationResp.getPhoneNumber(), "phone_number должен быть null");
        assertNull(registrationResp.getBirthDate(), "birth_date должен быть null");
        assertTrue(registrationResp.getBonusesAllowed(), "bonuses_allowed должен быть true");
        assertFalse(registrationResp.getIsVerified(), "is_verified должен быть false");
    }
}
