package leaderboard.Infrastructure.Framework.Configuration;

import leaderboard.Domain.AddGame.AddGameCommand;
import leaderboard.Domain.AddGame.AddGameCommandHandler;
import leaderboard.Domain.AddGame.GameAddedDomainEvent;
import leaderboard.Domain.AddGame.GameAddedDomainEventSubscriber;
import leaderboard.Domain.CreatePlayer.CreatePlayerCommand;
import leaderboard.Domain.CreatePlayer.CreatePlayerCommandHandler;
import leaderboard.Domain.CreatePlayer.PlayerCreatedDomainEvent;
import leaderboard.Domain.CreatePlayer.PlayerCreatedDomainEventSubscriber;
import leaderboard.Domain.Leaderboard;
import leaderboard.Domain.PlayerRepository;
import leaderboard.Infrastructure.Bus.Command.CommandBus;
import leaderboard.Infrastructure.Bus.Command.CommandBusMap;
import leaderboard.Infrastructure.Bus.Event.DomainEventPublisher;
import leaderboard.Infrastructure.Bus.Event.DomainEventPublisherMap;
import leaderboard.Infrastructure.Framework.Resources.LeaderboardResource;
import leaderboard.Infrastructure.Persistence.EventStorePostgres;
import leaderboard.Infrastructure.Persistence.LeaderboardRedis;
import leaderboard.Infrastructure.Persistence.PlayerRepositoryRedis;
import leaderboard.Types.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;

@Configuration
public class ApiConfiguration {

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
