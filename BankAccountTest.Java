package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount acc = new BankAccount("a@b.com", 200);
        assertEquals(200, acc.getBalance(), 0.001);
    }

    @Test
    void constructorTest() {
        BankAccount acc = new BankAccount("a@b.com", 200);
        assertEquals("a@b.com", acc.getEmail());
        assertEquals(200, acc.getBalance(), 0.001);

        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount("", 100));

        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount("a@b.com", -10));

        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount("a@b.com", 100.001));
    }

    @Test
    void withdrawTest() throws InsufficientFundsException {
        BankAccount acc = new BankAccount("a@b.com", 200);

        acc.withdraw(100);
        assertEquals(100, acc.getBalance(), 0.001);

        assertThrows(InsufficientFundsException.class,
                () -> acc.withdraw(300));

        assertThrows(IllegalArgumentException.class,
                () -> acc.withdraw(-10));

        assertThrows(IllegalArgumentException.class,
                () -> acc.withdraw(10.999));
    }

    @Test
    void depositTest() {
        BankAccount acc = new BankAccount("a@b.com", 100);

        acc.deposit(50);
        assertEquals(150, acc.getBalance(), 0.001);

        acc.deposit(0);
        assertEquals(150, acc.getBalance(), 0.001);

        assertThrows(IllegalArgumentException.class,
                () -> acc.deposit(-10));

        assertThrows(IllegalArgumentException.class,
                () -> acc.deposit(5.123));
    }

    @Test
    void transferTest() throws InsufficientFundsException {
        BankAccount a1 = new BankAccount("a@b.com", 100);
        BankAccount a2 = new BankAccount("c@d.com", 50);

        a1.transfer(a2, 25);
        assertEquals(75, a1.getBalance(), 0.001);
        assertEquals(75, a2.getBalance(), 0.001);

        a1.transfer(a2, 75);
        assertEquals(0, a1.getBalance(), 0.001);
        assertEquals(150, a2.getBalance(), 0.001);

        assertThrows(InsufficientFundsException.class,
                () -> a1.transfer(a2, 1));

        assertThrows(IllegalArgumentException.class,
                () -> a2.transfer(a1, -10));

        assertThrows(IllegalArgumentException.class,
                () -> a2.transfer(a1, 5.999));
    }

    @Test
    void emailValidationTest() {
        assertTrue(BankAccount.isEmailValid("a@b.com"));

        assertFalse(BankAccount.isEmailValid(""));
        assertFalse(BankAccount.isEmailValid("ab.com"));
        assertFalse(BankAccount.isEmailValid("@ab.com"));
        assertFalse(BankAccount.isEmailValid("a@bcom"));
    }

    @Test
    void amountValidationTest() {
        assertTrue(BankAccount.isAmountValid(10.50));
        assertTrue(BankAccount.isAmountValid(0.00));
        assertTrue(BankAccount.isAmountValid(25.0));

        assertFalse(BankAccount.isAmountValid(-10.25));
        assertFalse(BankAccount.isAmountValid(1.234));
        assertFalse(BankAccount.isAmountValid(0.001));
    }
}
