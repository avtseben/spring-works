package lessons.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// сканировать классы с анотацией @Component и наследников
// Если не передавать аргументов, сканирует в текущем пакете
@ComponentScan(basePackages = "lessons.services")
public class ServicesConfiguration {
}
