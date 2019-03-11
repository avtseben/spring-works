package lessons.exception;

import lessons.model.Account;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(Account account) {
        super("No money on account: " + account.toString());
    }
}