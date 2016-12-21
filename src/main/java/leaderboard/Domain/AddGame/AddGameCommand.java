package leaderboard.Domain.AddGame;

import leaderboard.Infrastructure.Bus.Command.Command;

final public class AddGameCommand implements Command {
    private final String id;
    private final String homePlayerId;
    private final int homeScore;
    private final String awayPlayerId;
    private final int awayScore;

    public AddGameCommand(String id, String homePlayerId, int homeScore, String awayPlayerId, int awayScore) {
        this.id = id;
        this.homePlayerId = homePlayerId;
        this.homeScore = homeScore;
        this.awayPlayerId = awayPlayerId;
        this.awayScore = awayScore;
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


}
