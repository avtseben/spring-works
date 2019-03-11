package lessons.busines;

import lessons.exception.InsufficientFundsException;
import lessons.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Base functionality of money transfer
 */
public abstract class AbstractMoneyTransfer implements MoneyTransfer {
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void transfer(Account from, Account to, int amount) {
        UUID tId = UUID.randomUUID();
        LOGGER.debug("transfer id: {} begin - from: {} to: {} amount: {}", tId, from, to, amount);
        if (from.getBalance() < amount) {
            throw new InsufficientFundsException(from);
        }
        doTransfer(from, to, amount);
        LOGGER.debug("transfer id: {} end - from: {} to: {} amount: {}", tId, from, to, amount);
    }

    abstract void doTransfer(Account from, Account to, int amount);
}
