package leaderboard.domain.addgame;

import com.fasterxml.jackson.core.JsonProcessingException;
import leaderboard.domain.Game;
import leaderboard.infrastructure.bus.command.Command;
import leaderboard.infrastructure.bus.command.Handler;
import leaderboard.infrastructure.bus.event.DomainEventPublisher;

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
