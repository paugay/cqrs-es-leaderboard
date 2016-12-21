package leaderboard.Domain.AddGame;

import leaderboard.Infrastructure.Bus.Command.Command;

final public class AddGameCommand implements Command {
    private final String homePlayerId;
    private final int homeScore;
    private final String awayPlayerId;
    private final int awayScore;

    public AddGameCommand(String homePlayerId, int homeScore, String awayPlayerId, int awayScore) {
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
