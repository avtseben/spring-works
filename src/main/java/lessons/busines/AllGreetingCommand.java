package lessons.busines;

import lessons.services.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class AllGreetingCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(AllGreetingCommand.class);

    private final Collection<GreetingService> greetingServices;

    public AllGreetingCommand(Collection<GreetingService> greetingServices) {
        this.greetingServices = greetingServices;
    }

    public AllGreetingCommand(Map<String,GreetingService> greetingServicesMap) {
        LOGGER.info(">>>Greeting Service map: {}", greetingServicesMap);
        this.greetingServices = greetingServicesMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }


    public void execute() {
        greetingServices.forEach(s -> LOGGER.info(">>>Service: {} sad: {}",s,s.sayGreeting()));
    }
}
