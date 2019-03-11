package lessons.busines;

import lessons.model.Account;

import java.util.concurrent.TimeUnit;

/**
 * Усовершенствованный способ с использованием tryLock()
 */
public class MoneyTransferTryLockImpl extends AbstractMoneyTransfer{
    private final int WAIT_SEC = 10;

    @Override
    public void doTransfer(Account from, Account to, int amount) {
        try {
            lockedTransfer(from, to, amount);
        } catch (InterruptedException e) {
            LOGGER.error("lockedTransfer interrupted");
            //NOP
        }
    }

    private void lockedTransfer(Account from, Account to, int amount) throws InterruptedException {
        if (from.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
            try {
                if (to.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
                    try {
                        from.withdraw(amount);
                        to.deposit(amount);
                    } finally {
                        to.getLock().unlock();
                    }
                }
            } finally {
                from.getLock().unlock();
            }
        } else {
            LOGGER.error("Cannot transfer. tryLock timeout");
        }
    }
}
