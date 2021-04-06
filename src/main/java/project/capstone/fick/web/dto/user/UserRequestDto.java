package project.capstone.fick.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRequestDto {

	@JsonProperty("UID")
    private Integer UID;
    private String name;

    @Builder
    public UserRequestDto(Integer UID, String name) {
    	this.name = name;
    	this.UID = UID;
	}
}
