package leaderboard.Infrastructure.Framework.Configuration;

import leaderboard.Domain.CreatePlayer.CreatePlayerCommand;
import leaderboard.Domain.CreatePlayer.CreatePlayerCommandHandler;
import leaderboard.Infrastructure.Bus.Command.CommandBus;
import leaderboard.Infrastructure.Bus.Command.CommandBusMap;
import leaderboard.Infrastructure.Bus.Event.DomainEventPublisher;
import leaderboard.Infrastructure.Bus.Event.DomainEventPublisherMap;
import leaderboard.Infrastructure.Framework.Resources.PlayerResource;
import leaderboard.Infrastructure.Persistence.EventStore.EventStorePostgres;
import leaderboard.Types.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class ApiConfiguration {

    @Autowired
    private DataSource datasource;

    @Bean
    public PlayerResource playerResource(CommandBus commandBus) {
        return new PlayerResource(commandBus);
    }

    @Bean
    public CommandBus commandBus() {
        CommandBusMap commandBusMap = new CommandBusMap();
        commandBusMap.register(CreatePlayerCommand.class, createPlayerCommandHandler());
        return commandBusMap;
    }

    @Bean
    public CreatePlayerCommandHandler createPlayerCommandHandler() {
        return new CreatePlayerCommandHandler(domainEventPublisher());
    }

    @Bean
    public DomainEventPublisher domainEventPublisher() {
        return new DomainEventPublisherMap(eventStore());
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
