package org.slotegrator.helpers;

import org.slotegrator.enums.GrantType;
import org.slotegrator.enums.Scope;
import org.slotegrator.model.request.TokenRequest;
import org.slotegrator.model.response.TokenResponse;
import org.slotegrator.steps.TokenSteps;

public class TokenHelper {

    private final TokenSteps tokenSteps = new TokenSteps();

    public String getGuestToken(String username) {
        TokenRequest tokenRequest = TokenRequest.builder()
                .grantType(GrantType.CLIENT_CREDENTIALS.toString())
                .scope(Scope.GUEST.getDefaultScope())
                .build();

        TokenResponse tokenResponse = tokenSteps.getToken(username, tokenRequest);
        return tokenResponse.getAccessToken();
    }
}
