package leaderboard.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import leaderboard.Domain.CreatePlayer.PlayerCreatedDomainEvent;
import leaderboard.Types.Aggregate.AggregateRoot;

public class Player extends AggregateRoot {
    final private String username;
    final private String name;
    final private String email;

    public Player(
            @JsonProperty("username") String username,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email) {
        this.username = username;
        this.name = name;
        this.email = email;
    }

    public static Player create(String username, String name, String email) throws JsonProcessingException {
        Player player = new Player(username, name, email);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPlayerString = objectMapper.writeValueAsString(player);

        player.raise(new PlayerCreatedDomainEvent(
                player.getUsername(),
                Player.class.getSimpleName(),
                jsonPlayerString));

        return player;
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

    @Override
    public String toString() {
        return "Player{" +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
