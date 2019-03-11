package lessons.busines;

import lessons.model.Account;

/**
 * Не"threadsafe" реализация transfer
 */
public class MoneyTransferSimpleImpl extends AbstractMoneyTransfer {
    @Override
    public void doTransfer(Account a, Account b, int amount) {
        LOGGER.debug("Before money transfer - a: {} b: {}", a, b);
        a.withdraw(amount);
        b.deposit(amount);
        LOGGER.debug("After money transfer - a: {} b: {}", a, b);
    }
}
