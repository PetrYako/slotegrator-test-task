package org.slotegrator.builders;

import org.slotegrator.model.request.PlayersRequest;

public class PlayersBuilder {

    /**
     * Создание пользователя для регистрации
     * @param username - логин
     * @param password - пароль + повторный
     * @param email - почта
     * @param name - имя
     * @param surname - фамилия
     * @param currency - код валюты по ISO4217
     * @return запрос для регистрации игрока
     */
    public PlayersRequest createPlayerForRegistration(String username, String password, String email, String name,
                                        String surname, String currency) {
        return PlayersRequest.builder()
                .username(username)
                .passwordChange(password)
                .passwordRepeat(password)
                .email(email)
                .name(name)
                .surname(surname)
                .currencyCode(currency)
                .build();
    }
}
