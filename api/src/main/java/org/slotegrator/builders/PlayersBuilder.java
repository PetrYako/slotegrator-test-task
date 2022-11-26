package org.slotegrator.builders;

import org.slotegrator.model.request.PlayersRequest;

public class PlayersBuilder {

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
