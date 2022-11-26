package org.slotegrator.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;
import org.slotegrator.model.ToStringImpl;

@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenRequest extends ToStringImpl {
    @JsonProperty("grant_type")
    private String grantType;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("scope")
    private String scope;
}
