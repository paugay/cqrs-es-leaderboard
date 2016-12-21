package leaderboard.infrastructure.spring.resources.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TODO: Potentially, extend the AddGameCommand class with the constructor annotation for jackson
 */
public class AddGameRequestDTO {
    private final String homePlayerId;
    private final int homeScore;
    private final String awayPlayerId;
    private final int awayScore;

    public AddGameRequestDTO(
            @JsonProperty("home_player_id") String homePlayerId,
            @JsonProperty("home_score") int homeScore,
            @JsonProperty("away_player_id") String awayPlayerId,
            @JsonProperty("away_score") int awayScore) {
        this.homePlayerId = homePlayerId;
        this.homeScore = homeScore;
        this.awayPlayerId = awayPlayerId;
        this.awayScore = awayScore;
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
}
