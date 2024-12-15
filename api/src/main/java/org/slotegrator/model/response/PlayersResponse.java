package org.slotegrator.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slotegrator.model.AbstractModel;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class PlayersResponse extends AbstractModel {
    private Long id;

    @JsonProperty("country_id")
    private String countryId;

    @JsonProperty("timezone_id")
    private String timezoneId;

    private String username;
    private String email;
    private String name;
    private String surname;
    private String gender;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("birthdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;

    @JsonProperty("bonuses_allowed")
    private Boolean bonusesAllowed;

    @JsonProperty("is_verified")
    private Boolean isVerified;
}
