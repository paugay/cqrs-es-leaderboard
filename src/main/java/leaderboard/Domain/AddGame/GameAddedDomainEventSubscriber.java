package leaderboard.Domain.AddGame;

import com.fasterxml.jackson.databind.ObjectMapper;
import leaderboard.Domain.Game;
import leaderboard.Domain.Leaderboard;
import leaderboard.Infrastructure.Bus.Event.DomainEvent;
import leaderboard.Infrastructure.Bus.Event.Subscriber;

import java.io.IOException;

public class GameAddedDomainEventSubscriber implements Subscriber {
    private final static int PERCENTAGE = 10;
    private Leaderboard leaderboard;

    public GameAddedDomainEventSubscriber(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    @Override
    public void subscribe(DomainEvent domainEvent) throws IOException {
        // protect
        GameAddedDomainEvent gameAddedDomainEvent = (GameAddedDomainEvent) domainEvent;

        ObjectMapper objectMapper = new ObjectMapper();
        Game game = objectMapper.readValue(domainEvent.getData(), Game.class);

        // ABSTRACT THIS SHIT

        int initialHomeScore = leaderboard.getPlayerScore(game.getHomePlayerId());
        int initialAwayScore = leaderboard.getPlayerScore(game.getAwayPlayerId());

        int currentHomeScore = initialHomeScore - Math.round(initialHomeScore / PERCENTAGE);
        int currentAwayScore = initialAwayScore - Math.round(initialAwayScore / PERCENTAGE);

        int inGame = Math.round(initialHomeScore / PERCENTAGE) + Math.round(initialAwayScore / PERCENTAGE);

        if (game.getHomeScore() == game.getAwayScore()) {
            currentHomeScore += Math.round(inGame / 2);
            currentAwayScore += Math.round(inGame / 2);
        } else if (game.getHomeScore() > game.getAwayScore()) {
            currentHomeScore += inGame;
        } else if (game.getHomeScore() < game.getAwayScore()) {
            currentAwayScore += inGame;
        }

        leaderboard.updatePlayer(game.getHomePlayerId(), currentHomeScore);
        leaderboard.updatePlayer(game.getAwayPlayerId(), currentAwayScore);
    }
}
