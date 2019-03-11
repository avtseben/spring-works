package lessons.starter;

import lessons.busines.AllGreetingCommand;
import lessons.busines.GreetingCommand;
import lessons.config.MainBeanConfiguration;
import lessons.config.ServicesConfiguration;
import lessons.services.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.Locale;

public class IoCStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(IoCStarter.class);

    public static void main(String[] args) {
        LOGGER.info("Starting IoC configuration");

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MainBeanConfiguration.class);
        context.registerShutdownHook(); //Для того чтобы вызывавлись методы @PreDestroy у бинов нужно регистрировать shutdownHook
        //В web-приложениях не нужно специально registerShutdownHook() (поскольку для них применяется отдельный тип контекста и подобный метод в них уже есть)
        GreetingService gs1 = context.getBean(GreetingService.class);
        GreetingService gs2 = context.getBean(GreetingService.class);
        LOGGER.info("Greeting: {}", gs1.sayGreeting());
        LOGGER.info("gs1: {}", gs1);
        LOGGER.info("gs2: {}", gs2);

        GreetingCommand greetingCommand = context.getBean("theCommand", GreetingCommand.class);
        GreetingCommand frenchGreetingCommand = context.getBean("frCommand", GreetingCommand.class);
        AllGreetingCommand allGreetingCommand = context.getBean(AllGreetingCommand.class);

        greetingCommand.execute();
        allGreetingCommand.execute();
        frenchGreetingCommand.execute();

        LOGGER.info("Message(default): " + context.getMessage("message", null, Locale.getDefault())); //Message properties example

        String[] beanNames = context.getBeanDefinitionNames();
        LOGGER.info("BeanName: {}", beanNames);


    }
}
