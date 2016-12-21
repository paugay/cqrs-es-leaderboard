package leaderboard.Domain.CreatePlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import leaderboard.Domain.Leaderboard;
import leaderboard.Domain.Player;
import leaderboard.Domain.PlayerRepository;
import leaderboard.Infrastructure.Bus.Event.DomainEvent;
import leaderboard.Infrastructure.Bus.Event.Subscriber;

import java.io.IOException;

public class PlayerCreatedDomainEventSubscriber implements Subscriber {
    private Leaderboard leaderboard;
    private PlayerRepository playerRepository;

    public PlayerCreatedDomainEventSubscriber(
            Leaderboard leaderboard,
            PlayerRepository playerRepository) {
        this.leaderboard = leaderboard;
        this.playerRepository = playerRepository;
    }

    @Override
    public void subscribe(DomainEvent domainEvent) throws IOException {
        // protect
        PlayerCreatedDomainEvent playerCreatedDomainEvent = (PlayerCreatedDomainEvent) domainEvent;

        ObjectMapper objectMapper = new ObjectMapper();
        // fix this
        Player player = objectMapper.readValue(domainEvent.getData(), Player.class);

        playerRepository.add(player);
        leaderboard.updatePlayer(player.getUsername(), 1000);
    }
}
