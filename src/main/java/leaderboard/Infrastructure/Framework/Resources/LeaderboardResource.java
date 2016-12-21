package leaderboard.Infrastructure.Framework.Resources;

import leaderboard.Domain.AddGame.AddGameCommand;
import leaderboard.Domain.CreatePlayer.CreatePlayerCommand;
import leaderboard.Infrastructure.Bus.Command.CommandBus;
import leaderboard.Infrastructure.Framework.Resources.DTO.AddGameRequestDTO;
import leaderboard.Infrastructure.Framework.Resources.DTO.CreatePlayerRequestDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/leaderboard", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LeaderboardResource {
    private CommandBus commandBus;

    public LeaderboardResource(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createPlayer(@RequestBody CreatePlayerRequestDTO createPlayerRequestDTO) {
        commandBus.dispatch(new CreatePlayerCommand(
                createPlayerRequestDTO.getId(),
                createPlayerRequestDTO.getUsername(),
                createPlayerRequestDTO.getName(),
                createPlayerRequestDTO.getEmail()));

        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public ResponseEntity getRanking() {

        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public ResponseEntity addGame(@RequestBody AddGameRequestDTO addGameRequestDTO) {
        commandBus.dispatch(new AddGameCommand(
                addGameRequestDTO.getHomePlayerId(),
                addGameRequestDTO.getHomeScore(),
                addGameRequestDTO.getAwayPlayerId(),
                addGameRequestDTO.getAwayScore()
        ));

        return ResponseEntity.accepted().build();
    }
}
