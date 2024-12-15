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
public class TokenRequest extends AbstractModel {
    @JsonProperty("grant_type")
    private String grantType;
    private String username;
    private String password;
    private String scope;
}
