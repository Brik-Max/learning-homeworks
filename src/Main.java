
public class Main {
    public static void main(String[] args) {

        ConcurrentBank bank = new ConcurrentBank();

        BankAccount account1 = bank.createAccount(1000);
        BankAccount account2 = bank.createAccount(500);

        System.out.println("Account 1 - balance: " + account1.getBalance());
        System.out.println("Account 2 - balance: " + account2.getBalance());

        Thread transferThread1 = new Thread(() -> bank.transfer(account1, account2, 200));
        Thread transferThread2 = new Thread(() -> bank.transfer(account2, account1, 100));

        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total balance: " + bank.getTotalBalance());

        System.out.println("Account 1 - balance: " + account1.getBalance());
        System.out.println("Account 2 - balance: " + account2.getBalance());

    }
}
