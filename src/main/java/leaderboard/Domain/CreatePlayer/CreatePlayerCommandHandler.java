package leaderboard.Domain.CreatePlayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import leaderboard.Domain.Player;
import leaderboard.Infrastructure.Bus.Command.Command;
import leaderboard.Infrastructure.Bus.Command.Handler;
import leaderboard.Infrastructure.Bus.Event.DomainEventPublisher;

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
