package lessons.busines;

import lessons.exception.InsufficientFundsException;
import lessons.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3-й способ борьбы с deadlock. Используя util.concurrent.Lock
 */
public class MoneyTransferLockImpl implements MoneyTransfer {
    private final static Logger LOGGER = LoggerFactory.getLogger(MoneyTransferLockImpl.class);

    @Override
    public void transfer(Account from, Account to, int amount) {
        if (from.getBalance() < amount) {
            throw new InsufficientFundsException(from);
        }
        Lock l = new ReentrantLock();
        l.lock();
        try {
            LOGGER.debug("Before money transfer - from: {} to: {}", from, to);
            from.withdraw(amount);
            to.deposit(amount);
            LOGGER.debug("After money transfer - from: {} to: {}", from, to);
        } finally {
            l.unlock();
        }
    }
}
