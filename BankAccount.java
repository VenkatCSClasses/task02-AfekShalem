package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * Creates a new BankAccount.
     *
     * @param email account identifier
     * @param startingBalance initial balance
     * @throws IllegalArgumentException if email or amount is invalid
     */
    public BankAccount(String email, double startingBalance) {
        if (!isEmailValid(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (!isAmountValid(startingBalance)) {
            throw new IllegalArgumentException("Invalid starting balance");
        }

        this.email = email;
        this.balance = startingBalance;
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Withdraws money from the account.
     *
     * @param amount amount to withdraw
     * @throws IllegalArgumentException if amount is invalid
     * @throws InsufficientFundsException if amount exceeds balance
     */
    public void withdraw(double amount) throws InsufficientFundsException {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Not enough money");
        }
        balance -= amount;
    }

    /**
     * Deposits money into the account.
     *
     * @param amount amount to deposit
     * @throws IllegalArgumentException if amount is invalid
     */
    public void deposit(double amount) {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Invalid deposit amount");
        }
        balance += amount;
    }

    /**
     * Transfers money from this account to another account.
     *
     * @param other destination account
     * @param amount amount to transfer
     * @throws IllegalArgumentException if amount is invalid
     * @throws InsufficientFundsException if insufficient balance
     */
    public void transfer(BankAccount other, double amount)
            throws InsufficientFundsException {

        if (other == null) {
            throw new IllegalArgumentException("Destination account cannot be null");
        }

        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Invalid transfer amount");
        }

        if (amount > balance) {
            throw new InsufficientFundsException("Not enough money");
        }

        this.balance -= amount;
        other.balance += amount;
    }

    /**
     * Validates email format.
     */
    public static boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) return false;

        int atIndex = email.indexOf('@');
        if (atIndex <= 0) return false;

        int dotIndex = email.indexOf('.', atIndex);
        if (dotIndex == -1 || dotIndex == email.length() - 1) return false;

        return true;
    }

    /**
     * Validates monetary amount.
     * Must be >= 0 and have at most two decimal places.
     */
    public static boolean isAmountValid(double amount) {
        if (amount < 0) return false;

        double rounded = Math.round(amount * 100) / 100.0;
        return rounded == amount;
    }
}
