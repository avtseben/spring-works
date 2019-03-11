package lessons.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service //для сканирования
@Qualifier("EN")//Доболнительный способ пометить реализацию
public class GreetingServiceEnImpl implements GreetingService {
    private final Logger LOGGER = LoggerFactory.getLogger(GreetingServiceEnImpl.class);

    @Override
    public String sayGreeting() {
        return "Hello";
    }

    @PostConstruct //Вызывается после создания бина
    public void postConst() {
        LOGGER.info(">>>Bean is created");
    }

    @PreDestroy //Вызывается перед уничтожением бина
    public void preDestroy() {
        LOGGER.info(">>>Уничтожение бина");
    }
}
