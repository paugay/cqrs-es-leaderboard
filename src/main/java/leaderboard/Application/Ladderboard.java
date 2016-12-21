package leaderboard.Application;

import leaderboard.Domain.Player;
import leaderboard.Domain.PlayerId;

interface Ladderboard {
    void addPlayer(Player player);

    void incrementPlayer(PlayerId playerId, int increment);
}
