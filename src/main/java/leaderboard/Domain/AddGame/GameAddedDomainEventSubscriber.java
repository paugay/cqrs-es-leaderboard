package leaderboard.Domain.AddGame;

import leaderboard.Infrastructure.Bus.Event.DomainEvent;
import leaderboard.Infrastructure.Bus.Event.Subscriber;

public class GameAddedDomainEventSubscriber implements Subscriber {
    @Override
    public void subscribe(DomainEvent domainEvent) {
        // protect
        PlayerCreatedDomainEvent playerCreatedDomainEvent = (PlayerCreatedDomainEvent) domainEvent;




    }
}
