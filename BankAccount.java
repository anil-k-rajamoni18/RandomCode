import java.io.Serializable;

class BankException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
    public BankException(final String message) {
        super(message);
    }
}
public class BankAccount implements Serializable {
    private final String accountNumber;
    private final String accountHolderName;
    private double balance;


    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0; // Initialize balance to zero
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println(amount + " deposited successfully.");
        } else {
            throw new BankException("Invalid Amount, Please deposit valid amount");
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.println(amount + " withdrawn successfully.");
        } else {
            throw new BankException("Insufficient funds. Withdrawal failed.");
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    // Method to display account information
    public void displayInfo() {
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Account Holder: " + this.accountHolderName);
        System.out.println("Balance: $" + this.balance);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                '}';
    }

    public static void main(String[] args) {
        // Create a new bank account instance
        try {
            BankAccount myAccount = new BankAccount("123456789", "Srikanth Rajamoni");

            // case-1
            myAccount.deposit(500.0);

            System.out.println("Current Balance: $" + myAccount.getBalance());

            myAccount.withdraw(200.0);

            System.out.println("Current Balance: $" + myAccount.getBalance());

            myAccount.displayInfo();

            // case-2
//            myAccount.deposit(-1000);
//            System.out.println("Current Balance: $" + myAccount.getBalance());

            // case-3
            myAccount.withdraw(-500);
            System.out.println("Current Balance: $" + myAccount.getBalance());

        } catch (Exception ex) {
            System.out.printf("Exception Occurred: %s", ex.getMessage());
        }

    }
}

