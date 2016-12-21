package leaderboard.domain.createplayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import leaderboard.domain.Leaderboard;
import leaderboard.domain.Player;
import leaderboard.domain.PlayerRepository;
import leaderboard.infrastructure.bus.event.DomainEvent;
import leaderboard.infrastructure.bus.event.Subscriber;

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
