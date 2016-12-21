package leaderboard.domain.createplayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import leaderboard.domain.Player;
import leaderboard.infrastructure.bus.command.Command;
import leaderboard.infrastructure.bus.command.Handler;
import leaderboard.infrastructure.bus.event.DomainEventPublisher;

public class CreatePlayerCommandHandler implements Handler {
    private DomainEventPublisher domainEventPublisher;

    public CreatePlayerCommandHandler(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public void handle(Command command) throws JsonProcessingException {
        // TODO fix this
        CreatePlayerCommand createPlayerCommand = (CreatePlayerCommand) command;

        Player player = Player.create(
                createPlayerCommand.getUsername(),
                createPlayerCommand.getName(),
                createPlayerCommand.getEmail());

        domainEventPublisher.publish(player.pullDomainEvents());
    }
}
