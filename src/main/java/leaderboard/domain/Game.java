package leaderboard.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import leaderboard.domain.addgame.GameAddedDomainEvent;
import leaderboard.types.aggregate.AggregateRoot;

public class Game extends AggregateRoot {
    private final String id;
    private final String homePlayerId;
    private final int homeScore;
    private final String awayPlayerId;
    private final int awayScore;

    public Game(
            @JsonProperty("id") String id,
            @JsonProperty("home_player_id") String homePlayerId,
            @JsonProperty("home_score") int homeScore,
            @JsonProperty("away_player_id") String awayPlayerId,
            @JsonProperty("away_score") int awayScore) {
        this.id = id;
        this.homePlayerId = homePlayerId;
        this.homeScore = homeScore;
        this.awayPlayerId = awayPlayerId;
        this.awayScore = awayScore;
    }

    public static Game create(String id, String homePlayerId, int homeScore, String awayPlayerId, int awayScore) throws JsonProcessingException {
        Game game = new Game(id, homePlayerId, homeScore, awayPlayerId, awayScore);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonGameString = objectMapper.writeValueAsString(game);

        // TODO: data, needs to be structured, I guess
        game.raise(new GameAddedDomainEvent(
                id,
                Game.class.getSimpleName(),
                jsonGameString));

        return game;
    }

    public String getId() {
        return id;
    }

    public String getHomePlayerId() {
        return homePlayerId;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public String getAwayPlayerId() {
        return awayPlayerId;
    }

    public int getAwayScore() {
        return awayScore;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", homePlayerId='" + homePlayerId + '\'' +
                ", homeScore=" + homeScore +
                ", awayPlayerId='" + awayPlayerId + '\'' +
                ", awayScore=" + awayScore +
                '}';
    }
}
