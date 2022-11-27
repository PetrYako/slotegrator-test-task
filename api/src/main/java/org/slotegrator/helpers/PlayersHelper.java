package org.slotegrator.helpers;

import com.github.javafaker.Faker;
import org.aeonbits.owner.ConfigFactory;
import org.slotegrator.builders.PlayersBuilder;
import org.slotegrator.config.Properties;
import org.slotegrator.model.request.PlayersRequest;
import org.slotegrator.model.response.PlayersResponse;
import org.slotegrator.steps.PlayersSteps;

public class PlayersHelper {

    private final TokenHelper tokenHelper = new TokenHelper();
    private final Faker faker = new Faker();
    private final PlayersSteps playersSteps = new PlayersSteps();
    private final PlayersBuilder playersBuilder = new PlayersBuilder();
    private final Properties properties = ConfigFactory.create(Properties.class);

    /**
     * Регистрация нового пользователя со случайными данными
     * @param newUsername - логин
     * @param password - пароль
     * @return ответ с данными созданного пользователя
     */
    public PlayersResponse registerNewPlayer(String newUsername, String password) {
        String guestUsername = properties.guestUsername();
        String email = newUsername + "@example.com";
        String name = faker.name().firstName();
        String surname = faker.name().lastName();

        PlayersRequest playersRequest= playersBuilder.createPlayerForRegistration(newUsername, password, email, name, surname, null);
        String accessToken = tokenHelper.getGuestToken(guestUsername);
        return playersSteps.registerPlayer(playersRequest, accessToken);
    }
}
