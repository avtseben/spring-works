package lessons.busines;

import lessons.exception.InsufficientFundsException;
import lessons.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Простая синхронизация без учёта порядка ресурсов. Легко пораждает deadlock
 */
public class MoneyTransferSyncImpl implements MoneyTransfer {
    private final static Logger LOGGER = LoggerFactory.getLogger(MoneyTransferSyncImpl.class);

    @Override
    public void transfer(Account a, Account b, int amount) {
        if (a.getBalance() < amount) {
            throw new InsufficientFundsException(a);
        }

        LOGGER.debug("Try sync on account a: {} hashcode: {}", a, a.hashCode());
        synchronized (a) {
            LOGGER.debug("Try sync on account b: {} hashcode: {}", b, b.hashCode());
            synchronized (b) {
                LOGGER.debug("Before money transfer - a: {} b: {}", a, b);
                a.withdraw(amount);
                b.deposit(amount);
                LOGGER.debug("After money transfer - a: {} b: {}", a, b);
            }
        }
        LOGGER.debug("Transfer complete - a: {}, b: {}", a, b);
    }
}
