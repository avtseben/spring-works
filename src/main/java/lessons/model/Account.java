package lessons.model;

public class Account {
    private String id;
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public Account(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public void withdraw(int amount) {
        this.balance -= amount;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }
}
