package lessons.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceFrImpl implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Bonjour";
    }
}
