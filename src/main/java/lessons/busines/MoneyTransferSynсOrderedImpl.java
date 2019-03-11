package lessons.busines;

import lessons.exception.InsufficientFundsException;
import lessons.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1-й способ борьбы с deadlock упорядочить ресурсы
 */
public class MoneyTransferSynсOrderedImpl implements MoneyTransfer {
    private final static Logger LOGGER = LoggerFactory.getLogger(MoneyTransferSynсOrderedImpl.class);

    @Override
    public void transfer(Account from, Account to, int amount) {
        if (from.getBalance() < amount) {
            throw new InsufficientFundsException(from);
        }
        OrderedAccount oa = orderedAccounts(from, to);
        LOGGER.debug("Try sync on account a: {} hashcode: {}", oa.getAcc1());
        synchronized (oa.getAcc1()) {
            LOGGER.debug("Try sync on account b: {} hashcode: {}", oa.getAcc2());
            synchronized (oa.getAcc2()) {
                LOGGER.debug("Before money transfer - from: {} to: {}", from, to);
                from.withdraw(amount);
                to.deposit(amount);
                LOGGER.debug("After money transfer - from: {} to: {}", from, to);
            }
        }
        LOGGER.debug("Transfer complete - a: {}, b: {}", from, to);
    }

    private OrderedAccount orderedAccounts(Account a, Account b) {
        if (a.getId().compareTo(b.getId()) < 0) {
            return new OrderedAccount(a, b);
        } else {
            return new OrderedAccount(b, a);
        }
    }

    private class OrderedAccount {
        private final Account acc1;
        private final Account acc2;

        OrderedAccount(Account acc1, Account acc2) {
            this.acc1 = acc1;
            this.acc2 = acc2;
        }

        private Account getAcc1() {
            return acc1;
        }

        private Account getAcc2() {
            return acc2;
        }
    }
}
