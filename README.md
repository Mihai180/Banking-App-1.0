# J. POO Morgan Chase & Co.

**Author**: Mihai-Ionuț Păunescu  

## Description
J. POO Morgan Chase & Co. is a Java project that models banking operations  
using design patterns such as **Visitor** and **Factory**. The application  
manages users, accounts, cards, transactions, and currency conversions.

The **Visitor** pattern separates the logic of each command and adds results  
to the output, adapting to each type of transaction.  
The **Factory** pattern creates and initializes commands, returning a specific  
object for each type of requested action.

## Features

- **User Management**: Adding, deleting, and displaying user details.
- **Bank Account Administration**: Creating and deleting accounts, setting  
  the minimum balance, and changing account interest rates.
- **Card Management**: Creating, deleting, and checking card status.
- **Financial Transactions**: Sending money, online payments, and splitting  
  payments among multiple accounts.
- **Detailed Reports**: Generating transaction and expense reports.
- **Currency Conversions**: Managing international currency exchanges.
- **JSON Interface**: Producing structured output in JSON format.

## Project Structure

### **Command Package**
The `command` package implements the specific commands of the application.  
Commands are created using the **CommandFactory**.

- **AddAccountCommand**: Adds a new bank account.
- **AddFundsCommand**: Adds funds to a specified account.
- **AddInterestCommand**: Applies interest to savings accounts.
- **ChangeInterestRateCommand**: Changes the interest rate of an account.
- **CheckCardStatusCommand**: Checks the status of a card.
- **CreateCardCommand**: Creates a new card for an account.
- **DeleteAccountCommand**: Deletes a bank account.
- **DeleteCardCommand**: Deletes a card associated with an account.
- **NotImplementedCommand**: Placeholder for unimplemented commands.
- **PayOnlineCommand**: Makes online payments using a card.
- **PrintTransactionsCommand**: Displays the transaction history.
- **PrintUsersCommand**: Displays the list of system users.
- **ReportCommand**: Generates detailed transaction reports.
- **SendMoneyCommand**: Transfers money between accounts.
- **SetAliasCommand**: Sets an alias for an account.
- **SetMinBalanceCommand**: Configures the minimum balance for accounts.
- **SpendingsReportCommand**: Generates expense reports.
- **SplitPaymentCommand**: Performs split payments among multiple accounts.

### **Exception Package**
The `exception` package contains the custom exceptions used in the system:

- **AccountCanNotBeDeletedException**: The account cannot be deleted.
- **AccountNotFoundException**: The specified account was not found.
- **CardIsUsedException**: The card is already used in a transaction.
- **CardNotFoundException**: The specified card was not found.
- **FrozenCardException**: The card is locked for use.
- **InsufficientFundsException**: Insufficient funds for the transaction.
- **NoExchangeRateException**: The required exchange rate is unavailable.
- **UnauthorizedAccessException**: User access is unauthorized.
- **UserAlreadyExistsException**: The user already exists in the system.
- **UserNotFoundException**: The specified user was not found.

### **Model Package**
The `model` package contains the main data structures of the application.

#### **account Subpackage**
- **Account**: The base class for accounts.
- **ClassicAccount**: Classic accounts with standard features.
- **SavingsAccount**: Savings accounts that accrue interest.

#### **card Subpackage**
- **Card**: The base class for cards.
- **OneTimePayCard**: Cards usable only once.
- **RegularCard**: Regular cards associated with accounts.

#### **transaction Subpackage**
- **AccountCreationTransaction**: Creates a new account.
- **AccountDeletionErrorTransaction**: Error when deleting an account.
- **CardCreationTransaction**: Creates a card for an account.
- **CardDeletionTransaction**: Deletes a card from the system.
- **CardPaymentTransaction**: Payment made using a card.
- **FrozenCardTransaction**: Locks a card for use.
- **InsufficientFundsTransaction**: Error for insufficient funds.
- **InterestRateChangeTransaction**: Changes the interest rate of an account.
- **MinimumAmountOfFundsTransaction**: Sets the required minimum balance.
- **SendMoneyTransaction**: Transfers money between accounts.
- **SplitPaymentTransaction**: Splits a payment between multiple accounts.

### **Service Package**
The `service` package implements the main logic of the application:

- **AccountService**: Manages bank accounts.
- **CardService**: Handles cards associated with accounts.
- **ExchangeService**: Performs currency conversions.
- **TransactionService**: Manages system transactions.
- **UserService**: Administers users and their associated data.

### **Visitor Package**
The `visitor` package implements the **Visitor** design pattern.

#### **command Subpackage**
- **CommandVisitor**: The interface for visiting commands.
- **ConcreteCommandVisitor**: The implementation of command logic.

#### **transaction Subpackage**
- **TransactionVisitor**: The interface for visiting transactions.
- **ConcreteTransactionVisitor**: The implementation of transaction logic 
  (generating specific output for each transaction).
