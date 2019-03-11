package lessons.config;

import lessons.busines.MoneyTransfer;
import lessons.busines.MoneyTransferSimpleImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MoneyTransferConfiguration {

    @Bean
    public MoneyTransfer moneyTransfer() {
        return new MoneyTransferSimpleImpl();
    }
}
