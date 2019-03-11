package lessons.busines;

import lessons.services.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetingCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingCommand.class);

    private final String prefix;
    private final GreetingService greetingService;

    public GreetingCommand(String prefix, GreetingService greetingService) {
        this.prefix = prefix;
        this.greetingService = greetingService;
    }

    public void execute() {
        LOGGER.info(prefix + greetingService.sayGreeting());
    }
}
