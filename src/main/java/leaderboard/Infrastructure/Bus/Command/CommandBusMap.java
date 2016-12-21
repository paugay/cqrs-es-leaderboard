package leaderboard.Infrastructure.Bus.Command;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final public class CommandBusMap implements CommandBus {
    private static final Logger LOG = LoggerFactory.getLogger(CommandBusMap.class);

    private Map<Class, List<Handler>> commandHandlerMap;

    public CommandBusMap() {
        commandHandlerMap = new HashMap<>();
    }

    @Override
    public void register(Class<? extends Command> commandType, Handler handler) {
        if (!commandHandlerMap.containsKey(commandType)) {
            commandHandlerMap.put(commandType, new ArrayList<>());
        }

        commandHandlerMap.get(commandType).add(handler);
    }

    @Override
    public void dispatch(Command command) {
        LOG.debug("CommandBus dispatch [{}]", command);

        Class commandType = command.getClass();
        if (commandHandlerMap.containsKey(commandType)) {
            commandHandlerMap.get(commandType).stream().forEach(handler -> {
                try {
                    handler.handle(command);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
