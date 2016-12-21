package leaderboard.infrastructure.persistence;

import leaderboard.infrastructure.bus.event.DomainEvent;
import leaderboard.types.EventStore;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EventStorePostgres implements EventStore {
    private JdbcTemplate jdbcTemplate;

    public EventStorePostgres(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DomainEvent> load(String id) {
        // TODO
        return null;
    }

    @Override
    public void append(DomainEvent domainEvent) {
        String insertTableSQL = "INSERT INTO eventstore"
                + "(event_id, event_type, aggregate_id, aggregate_type, data, occured_on) VALUES"
                + "(?,?,?,?,?,?)";

        jdbcTemplate.update(
                insertTableSQL,
                domainEvent.getEventId().toString(),
                domainEvent.getEventType(),
                domainEvent.getAggregateId(),
                domainEvent.getAggregateType(),
                domainEvent.getData(),
                domainEvent.getOccuredOn());
    }
}
