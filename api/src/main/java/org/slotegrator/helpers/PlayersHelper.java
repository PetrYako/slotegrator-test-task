package org.slotegrator.helpers;

import com.github.javafaker.Faker;
import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.util.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
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
     * Регистарция пользователя со случайными данными
     * @return овтет с данными созданного пользователя
     */
    public PlayersResponse registerNewPlayer() {
        String username = "AT-" + RandomUtils.nextLong();
        String password = Base64.encode(RandomStringUtils.randomAlphanumeric(8).getBytes());

        return registerNewPlayerWith(username, password);
    }

    /**
     * Регистрация нового пользователя с заданным логином и паролем
     * @param newUsername - логин
     * @param password - пароль
     * @return ответ с данными созданного пользователя
     */
    public PlayersResponse registerNewPlayerWith(String newUsername, String password) {
        String guestUsername = properties.guestUsername();
        String email = newUsername + "@example.com";
        String name = faker.name().firstName();
        String surname = faker.name().lastName();

        /*
        currency=null, т к на данный момент при отправке запроса с currency_code=EUR возвращается ошибка
        + данный параметр optional

        это можно увидеть в тесте PlayerRegistrationTest
         */
        PlayersRequest playersRequest= playersBuilder.createPlayerForRegistration(newUsername, password, email, name, surname, null);
        String accessToken = tokenHelper.getGuestToken(guestUsername);
        return playersSteps.registerPlayer(playersRequest, accessToken);
    }
}
