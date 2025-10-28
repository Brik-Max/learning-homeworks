

public class BankAccount {
    private final int id;
    private double balance;

    public BankAccount(int id, double initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    public synchronized double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }
}