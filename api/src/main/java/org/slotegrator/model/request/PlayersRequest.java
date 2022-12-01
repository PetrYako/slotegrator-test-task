package org.slotegrator.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.slotegrator.model.AbstractModel;

@Data
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayersRequest extends AbstractModel {
    @JsonProperty(required = true)
    private String username;

    @JsonProperty(value = "password_change", required = true)
    private String passwordChange;

    @JsonProperty(value = "password_repeat", required = true)
    private String passwordRepeat;

    @JsonProperty(required = true)
    private String email;
    private String name;
    private String surname;

    @JsonProperty("currency_code")
    private String currencyCode;
}
