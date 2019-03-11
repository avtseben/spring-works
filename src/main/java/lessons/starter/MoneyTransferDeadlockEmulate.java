package lessons.starter;

import lessons.busines.MoneyTransfer;
import lessons.busines.MoneyTransferSyncImpl;
import lessons.model.Account;

public class MoneyTransferDeadlockEmulate {

    public static void main(String[] args) {
        MoneyTransfer moneyTransfer = new MoneyTransferSyncImpl();
        Account a = new Account("a", 1000);
        Account b = new Account("b", 1000);

        for (int i = 0; i < 2; i++) {
            if (i % 2 == 0) {
                new Thread(() -> moneyTransfer.transfer(a, b, 10)).start();
            } else {
                new Thread(() -> moneyTransfer.transfer(b, a, 10)).start();
            }
        }
        //To see deadlock use CLI:
        //jps | grep MoneyTransferDeadlockEmulate | jstack `awk '{print $1}'`
    }
}
