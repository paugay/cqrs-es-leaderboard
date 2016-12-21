package leaderboard.infrastructure.spring.config;

import leaderboard.domain.addgame.AddGameCommand;
import leaderboard.domain.addgame.AddGameCommandHandler;
import leaderboard.domain.addgame.GameAddedDomainEvent;
import leaderboard.domain.addgame.GameAddedDomainEventSubscriber;
import leaderboard.domain.createplayer.CreatePlayerCommand;
import leaderboard.domain.createplayer.CreatePlayerCommandHandler;
import leaderboard.domain.createplayer.PlayerCreatedDomainEvent;
import leaderboard.domain.createplayer.PlayerCreatedDomainEventSubscriber;
import leaderboard.domain.Leaderboard;
import leaderboard.domain.PlayerRepository;
import leaderboard.infrastructure.bus.command.CommandBus;
import leaderboard.infrastructure.bus.command.CommandBusMap;
import leaderboard.infrastructure.bus.event.DomainEventPublisher;
import leaderboard.infrastructure.bus.event.DomainEventPublisherMap;
import leaderboard.infrastructure.spring.resources.LeaderboardResource;
import leaderboard.infrastructure.persistence.EventStorePostgres;
import leaderboard.infrastructure.persistence.LeaderboardRedis;
import leaderboard.infrastructure.persistence.PlayerRepositoryRedis;
import leaderboard.types.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;

@Configuration
public class LeaderboardConfig {

    @Autowired
    private DataSource datasource;

    @Bean
    public LeaderboardResource leaderboardResource() {
        return new LeaderboardResource(commandBus(), leaderboard());
    }

    @Bean
    public CommandBus commandBus() {
        CommandBusMap commandBusMap = new CommandBusMap();
        commandBusMap.register(AddGameCommand.class, addGameCommandHandler());
        commandBusMap.register(CreatePlayerCommand.class, createPlayerCommandHandler());
        return commandBusMap;
    }

    @Bean
    public AddGameCommandHandler addGameCommandHandler() {
        return new AddGameCommandHandler(domainEventPublisher());
    }

    @Bean
    public CreatePlayerCommandHandler createPlayerCommandHandler() {
        return new CreatePlayerCommandHandler(domainEventPublisher());
    }

    @Bean
    public DomainEventPublisher domainEventPublisher() {
        DomainEventPublisherMap domainEventPublisherMap = new DomainEventPublisherMap(eventStore());
        domainEventPublisherMap.register(GameAddedDomainEvent.class, gameAddedDomainEventSubscriber());
        domainEventPublisherMap.register(PlayerCreatedDomainEvent.class, playerCreatedDomainEventSubscriber());
        return domainEventPublisherMap;
    }

    @Bean
    public PlayerCreatedDomainEventSubscriber playerCreatedDomainEventSubscriber() {
        return new PlayerCreatedDomainEventSubscriber(
                leaderboard(),
                playerRepository());
    }

    @Bean
    public GameAddedDomainEventSubscriber gameAddedDomainEventSubscriber() {
        return new GameAddedDomainEventSubscriber(leaderboard());
    }

    @Bean
    public Leaderboard leaderboard() {
        return new LeaderboardRedis(
                jedis(),
                playerRepository());
    }

    @Bean
    public PlayerRepository playerRepository() {
        return new PlayerRepositoryRedis(jedis());
    }

    @Bean
    public Jedis jedis() {
        return new Jedis("localhost");
    }

    @Bean
    public EventStore eventStore() {
        return new EventStorePostgres(jdbcTemplate());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(datasource);
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(datasource);
    }
}
