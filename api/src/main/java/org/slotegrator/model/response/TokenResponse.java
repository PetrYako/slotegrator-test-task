package org.slotegrator.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slotegrator.model.ToStringImpl;

@Data
@EqualsAndHashCode(callSuper = false)
public class TokenResponse extends ToStringImpl {
    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;
}


