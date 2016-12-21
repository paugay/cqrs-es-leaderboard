package leaderboard.Domain.CreatePlayer;

import leaderboard.Infrastructure.Bus.Event.DomainEvent;
import leaderboard.Infrastructure.Bus.Event.Subscriber;

public class PlayerCreatedDomainEventSubscriber implements Subscriber {
    @Override
    public void subscribe(DomainEvent domainEvent) {
        // protect
        PlayerCreatedDomainEvent playerCreatedDomainEvent = (PlayerCreatedDomainEvent) domainEvent;




    }
}
