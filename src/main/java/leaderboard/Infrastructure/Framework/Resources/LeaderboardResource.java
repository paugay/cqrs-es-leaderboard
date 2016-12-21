package leaderboard.Infrastructure.Framework.Resources;

import leaderboard.Domain.AddGame.AddGameCommand;
import leaderboard.Domain.CreatePlayer.CreatePlayerCommand;
import leaderboard.Domain.Leaderboard;
import leaderboard.Domain.Ranking;
import leaderboard.Infrastructure.Bus.Command.CommandBus;
import leaderboard.Infrastructure.Framework.Resources.DTO.AddGameRequestDTO;
import leaderboard.Infrastructure.Framework.Resources.DTO.CreatePlayerRequestDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/leaderboard", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LeaderboardResource {
    private CommandBus commandBus;
    private Leaderboard leaderboard;

    public LeaderboardResource(
            CommandBus commandBus,
            Leaderboard leaderboard) {
        this.commandBus = commandBus;
        this.leaderboard = leaderboard;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createPlayer(@RequestBody CreatePlayerRequestDTO createPlayerRequestDTO) {
        commandBus.dispatch(new CreatePlayerCommand(
                createPlayerRequestDTO.getUsername(),
                createPlayerRequestDTO.getName(),
                createPlayerRequestDTO.getEmail()));

        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public ResponseEntity getRanking() {
        return ResponseEntity.ok(leaderboard.getRanking());
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public ResponseEntity addGame(@RequestBody AddGameRequestDTO addGameRequestDTO) {
        String gameId = UUID.randomUUID().toString();

        // TODO consistency here with the "createPlayer"
        commandBus.dispatch(new AddGameCommand(
                gameId,
                addGameRequestDTO.getHomePlayerId(),
                addGameRequestDTO.getHomeScore(),
                addGameRequestDTO.getAwayPlayerId(),
                addGameRequestDTO.getAwayScore()
        ));

        return ResponseEntity.accepted().build();
    }
}
