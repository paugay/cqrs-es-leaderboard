package leaderboard.Domain.CreatePlayer;

import leaderboard.Domain.Player;
import leaderboard.Domain.PlayerId;
import leaderboard.Infrastructure.Bus.Command.Command;
import leaderboard.Infrastructure.Bus.Command.Handler;
import leaderboard.Infrastructure.Bus.Event.DomainEventPublisher;

public class CreatePlayerCommandHandler implements Handler {
    private DomainEventPublisher domainEventPublisher;

    public CreatePlayerCommandHandler(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public void handle(Command command) {
        // TODO fix this
        CreatePlayerCommand createPlayerCommand = (CreatePlayerCommand) command;

        Player player = Player.create(
                new PlayerId(createPlayerCommand.getId()),
                createPlayerCommand.getUsername(),
                createPlayerCommand.getName(),
                createPlayerCommand.getEmail());

        domainEventPublisher.publish(player.pullDomainEvents());
    }
}
