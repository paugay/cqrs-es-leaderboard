package leaderboard.Infrastructure.Framework.Resources.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePlayerRequestDTO {
    private final String username;
    private final String name;
    private final String email;

    public CreatePlayerRequestDTO(
            @JsonProperty("username") String username,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email) {
        this.username = username;
        this.name = name;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
