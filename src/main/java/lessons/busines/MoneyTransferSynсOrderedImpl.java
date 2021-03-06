package lessons.busines;

import lessons.model.Account;

/**
 * 1-й способ борьбы с deadlock упорядочить ресурсы
 */
public class MoneyTransferSynсOrderedImpl extends AbstractMoneyTransfer {
    @Override
    public void doTransfer(Account from, Account to, int amount) {
        final OrderedAccounts oa = orderAccounts(from, to);
        LOGGER.debug("Try sync on account: {}", oa.getAcc1());
        synchronized (oa.getAcc1()) {
            LOGGER.debug("Try sync on account: {}", oa.getAcc2());
            synchronized (oa.getAcc2()) {
                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }

    private OrderedAccounts orderAccounts(Account a, Account b) {
        if (a.getId().compareTo(b.getId()) < 0) {
            return new OrderedAccounts(a, b);
        } else {
            return new OrderedAccounts(b, a);
        }
    }

    private class OrderedAccounts {
        private final Account acc1;
        private final Account acc2;

        OrderedAccounts(Account acc1, Account acc2) {
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
