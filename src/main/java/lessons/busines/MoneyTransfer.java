package lessons.busines;

import lessons.model.Account;

public interface MoneyTransfer {
    void transfer(Account a, Account b, int amount);
}
