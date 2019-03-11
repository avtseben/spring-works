package lessons.busines;

import lessons.exception.InsufficientFundsException;
import lessons.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoneyTransferSimpleImpl implements MoneyTransfer {
    private final static Logger LOGGER = LoggerFactory.getLogger(MoneyTransferSimpleImpl.class);

    @Override
    public void transfer(Account a, Account b, int amount) {
        if (a.getBalance() < amount) {
            throw new InsufficientFundsException(a);
        }

        LOGGER.debug("Before money transfer - a: {} b: {}", a, b);
        a.withdraw(amount);
        b.deposit(amount);
        LOGGER.debug("After money transfer - a: {} b: {}", a, b);
    }
}
