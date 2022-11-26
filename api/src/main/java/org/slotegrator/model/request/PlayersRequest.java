package org.slotegrator.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;
import org.slotegrator.model.ToStringImpl;

@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayersRequest extends ToStringImpl {
    @JsonProperty(value = "username", required = true)
    private String username;

    @JsonProperty(value = "password_change", required = true)
    private String passwordChange;

    @JsonProperty(value = "password_repeat", required = true)
    private String passwordRepeat;

    @JsonProperty(value = "email", required = true)
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("currency_code")
    private String currencyCode;
}
