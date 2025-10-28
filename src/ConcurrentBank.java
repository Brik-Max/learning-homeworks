import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentBank {
    private final AtomicInteger nextAccountId = new AtomicInteger(0);
    private final Map<Integer, BankAccount> accounts = new ConcurrentHashMap<>();
    private final AtomicInteger totalBalance = new AtomicInteger(0);

    public BankAccount createAccount(int initialBalance) {
        int id = nextAccountId.incrementAndGet();
        BankAccount account = new BankAccount(id, initialBalance);
        accounts.put(id, account);
        totalBalance.addAndGet(initialBalance);
        return account;
    }

    public boolean transfer(BankAccount from, BankAccount to, int amount) {

        if (from == to || amount <= 0) {
            return false;
        }

        BankAccount firstLock = from.getId() < to.getId() ? from : to;
        BankAccount secondLock = from.getId() < to.getId() ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                    return true;
                }
                return false;
            }
        }
    }

    public int getTotalBalance() {
        return totalBalance.get();
    }
}