package lessons.busines;

import lessons.model.Account;

/**
 * Простая синхронизация без учёта порядка ресурсов. Легко пораждает deadlock
 */
public class MoneyTransferSyncImpl extends AbstractMoneyTransfer {
    @Override
    public void doTransfer(Account a, Account b, int amount) {
        LOGGER.debug("Try sync on account a: {} hashcode: {}", a, a.hashCode());
        synchronized (a) {
            LOGGER.debug("Try sync on account b: {} hashcode: {}", b, b.hashCode());
            synchronized (b) {
                a.withdraw(amount);
                b.deposit(amount);
            }
        }
    }
}
