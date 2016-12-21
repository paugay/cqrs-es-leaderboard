package leaderboard.Domain.AddGame;

import com.fasterxml.jackson.core.JsonProcessingException;
import leaderboard.Domain.Game;
import leaderboard.Infrastructure.Bus.Command.Command;
import leaderboard.Infrastructure.Bus.Command.Handler;
import leaderboard.Infrastructure.Bus.Event.DomainEventPublisher;

public class AddGameCommandHandler implements Handler {
    private DomainEventPublisher domainEventPublisher;

    public AddGameCommandHandler(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public void handle(Command command) throws JsonProcessingException {
        // TODO fix this shit!
        AddGameCommand addGameCommand = (AddGameCommand) command;

        Game game = Game.create(
                addGameCommand.getId(),
                addGameCommand.getHomePlayerId(),
                addGameCommand.getHomeScore(),
                addGameCommand.getAwayPlayerId(),
                addGameCommand.getAwayScore());

        domainEventPublisher.publish(game.pullDomainEvents());
    }
}
