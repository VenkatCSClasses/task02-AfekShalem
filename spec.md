# spec.md

## BankAccount Specification

### Overview

The `BankAccount` class represents a simple bank account that stores:

* An account email (identifier)
* A monetary balance

The class supports:

* Validation of email addresses
* Validation of monetary amounts
* Deposits
* Withdrawals
* Transfers between accounts

All monetary values must follow standard bank account rules:

* Non-negative
* No more than two decimal places

---

## Class: BankAccount

### Fields

* `email: String`
  Stores the account holder’s email address.

* `balance: double`
  Stores the current account balance.

---

## Constructor

### `BankAccount(String email, double startingBalance)`

Creates a new bank account.

#### Preconditions

* Email must be valid.
* Starting balance must:

  * Be non-negative
  * Have at most two decimal places

#### Postconditions

* Account is created with:

  * The given email
  * The given starting balance

#### Exceptions

* Throws `IllegalArgumentException` if:

  * Email is invalid
  * Starting balance is invalid

---

## Methods

### `double getBalance()`

Returns the current balance.

#### Postconditions

* Does not modify account state.

---

### `String getEmail()`

Returns the account email.

#### Postconditions

* Does not modify account state.

---

### `void withdraw(double amount)`

Removes money from the account.

#### Preconditions

* Amount must be valid.
* Amount must not exceed current balance.

#### Postconditions

* Balance is reduced by the withdrawal amount.

#### Exceptions

* `IllegalArgumentException` if amount is invalid.
* `InsufficientFundsException` if amount exceeds balance.

---

### `void deposit(double amount)`

Adds money to the account.

#### Preconditions

* Amount must be valid.

#### Postconditions

* Balance increases by the deposit amount.

#### Exceptions

* `IllegalArgumentException` if amount is invalid.

---

### `void transfer(BankAccount other, double amount)`

Transfers money from this account to another account.

#### Preconditions

* Amount must be valid.
* Amount must not exceed current balance.
* Destination account must not be null.

#### Postconditions

* Sender’s balance decreases by amount.
* Receiver’s balance increases by amount.

#### Exceptions

* `IllegalArgumentException` if amount is invalid.
* `InsufficientFundsException` if insufficient balance.

---

## Static Methods

### `boolean isEmailValid(String email)`

Determines whether an email address is valid.

#### Rules

An email is valid if:

* It is not null
* It is not empty
* It contains exactly one '@'
* There is at least one character before '@'
* There is a '.' after the '@'
* The '.' is not the final character

---

### `boolean isAmountValid(double amount)`

Determines whether a monetary amount is valid.

#### Rules

A valid amount:

* Is greater than or equal to 0
* Has at most two decimal places

Examples:

Valid:

* 0.00
* 10.50
* 25.0

Invalid:

* -1.00
* 1.234
* 5.999

---

## Exception: InsufficientFundsException

Thrown when a withdrawal or transfer amount exceeds the available balance.
