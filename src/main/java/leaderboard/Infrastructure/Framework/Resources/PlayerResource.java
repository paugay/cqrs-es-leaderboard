package leaderboard.Infrastructure.Framework.Resources;

import leaderboard.Domain.CreatePlayer.CreatePlayerCommand;
import leaderboard.Infrastructure.Bus.Command.CommandBus;
import leaderboard.Infrastructure.Framework.Resources.DTO.CreatePlayerRequestDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PlayerResource {
    private CommandBus commandBus;

    public PlayerResource(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public ResponseEntity createPlayer(@RequestBody CreatePlayerRequestDTO createPlayerRequestDTO) {
        commandBus.dispatch(new CreatePlayerCommand(
                createPlayerRequestDTO.getId(),
                createPlayerRequestDTO.getUsername(),
                createPlayerRequestDTO.getName(),
                createPlayerRequestDTO.getEmail()));

        return ResponseEntity.accepted().build();
    }
}
