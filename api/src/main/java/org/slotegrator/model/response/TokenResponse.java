package org.slotegrator.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slotegrator.model.AbstractModel;

@Data
@EqualsAndHashCode(callSuper = false)
public class TokenResponse extends AbstractModel {
    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("access_token")
    private String accessToken;

    /**
     * Данный параметр не приходит в ответе от oauth2
     * Но в примерах он присутствует
     * Возможно дефект, но спецификации нет
     */
    @JsonProperty("refresh_token")
    private String refreshToken;
}


