package leaderboard.infrastructure.bus.event;

import java.io.IOException;

public interface Subscriber {
    public void subscribe(DomainEvent domainEvent) throws IOException;
}
