package lessons.config;

import lessons.busines.AllGreetingCommand;
import lessons.busines.GreetingCommand;
import lessons.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Collection;
import java.util.Map;

@Configuration
@Import(ServicesConfiguration.class)
@PropertySource("classpath:greeting.properties")
public class MainBeanConfiguration {

    @Value("${greeting.prefix}") //TODO разобраться как property placeholder подключить
    private String prefix;
    @Autowired
    private Collection<GreetingService> greetingServices; // Все имплементации greetingService
    @Autowired //к Map, где ключами являются имена бинов, значения - сами бины
    private Map<String, GreetingService> serviceMap; //Все импелентации с именами бинов
    @Autowired
    @Qualifier("greetingServiceFrImpl") //Указать какую именно реализацию выбрать
    private GreetingService greetingServiceFr;

    @Bean(name = {"theCommand", "gCommand"}) // - ручное именование бина
    public GreetingCommand greetingCommand(@Qualifier("EN") GreetingService greetingService) {
        return new GreetingCommand(prefix, greetingService);
    }

    @Bean(name = "frCommand") // - ручное именование бина
    public GreetingCommand greetingCommandFr() {
        return new GreetingCommand(prefix, greetingServiceFr);
    }

    @Bean
    @Description("Команда в которой все виды приветствия") //Описание бина
    public AllGreetingCommand allGreetingCommand() {
        return new AllGreetingCommand(serviceMap);
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
}
