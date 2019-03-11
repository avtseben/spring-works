package lessons.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Primary
@Service //для сканирования
public class GreetingServiceRuImpl implements GreetingService {
    private final Logger LOGGER = LoggerFactory.getLogger(GreetingServiceRuImpl.class);

    @Override
    public String sayGreeting() {
        return "Привет";
    }

    @PostConstruct //Вызывается после создания бина
    public void postConst() {
        LOGGER.info(">>>Создал бин");
    }

    @PreDestroy //Вызывается перед уничтожением бина
    public void preDestroy() {
        LOGGER.info(">>>Уничтожение бина");
    }
}
