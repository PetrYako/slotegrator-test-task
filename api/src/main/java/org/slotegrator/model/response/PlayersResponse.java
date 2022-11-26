package org.slotegrator.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slotegrator.model.ToStringImpl;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class PlayersResponse extends ToStringImpl {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("country_id")
    private String countryId;

    @JsonProperty("timezone_id")
    private String timezoneId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("gender")
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
