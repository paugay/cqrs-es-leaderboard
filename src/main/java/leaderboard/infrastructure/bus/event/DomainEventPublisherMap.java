package leaderboard.infrastructure.bus.event;

import leaderboard.types.EventStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final public class DomainEventPublisherMap implements DomainEventPublisher {
    private static final Logger LOG = LoggerFactory.getLogger(DomainEventPublisherMap.class);

    private EventStore eventStore;

    private Map<Class, List<Subscriber>> domainEventSubscriberMap;

    public DomainEventPublisherMap(EventStore eventStore) {
        this.eventStore = eventStore;

        domainEventSubscriberMap = new HashMap<>();
    }

    @Override
    public void register(Class<? extends DomainEvent> domainEventType, Subscriber subscriber) {
        if (!domainEventSubscriberMap.containsKey(domainEventType)) {
            domainEventSubscriberMap.put(domainEventType, new ArrayList<>());
        }

        domainEventSubscriberMap.get(domainEventType).add(subscriber);
    }

    @Override
    public void publish(List<DomainEvent> domainEventList) {
        for (DomainEvent domainEvent : domainEventList) {
            LOG.debug("DomainEventPublisher publish [{}]", domainEvent);

            // TODO: unsure if that shall be done here or not ...

            // STORE event
            eventStore.append(domainEvent);

            Class domainEventType = domainEvent.getClass();
            if (domainEventSubscriberMap.containsKey(domainEventType)) {
                domainEventSubscriberMap.get(domainEventType).stream().forEach(subscriber -> {
                    try {
                        subscriber.subscribe(domainEvent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
}
