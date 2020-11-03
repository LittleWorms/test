package int101.banking;

import int101.base.Person;
import java.math.BigDecimal;

public class BankAccount {
    private static int nextAccountNo;
    private final int accountNo;
    private final String accountName;
    private final Person accountOwner;
    private final AccountHistory history;
    private BigDecimal balance;

    public BankAccount(String accountName, Person accountOwner) {
        this.accountNo = nextAccountNo++;
        this.accountName = accountName;
        this.accountOwner = accountOwner;
        this.history = new AccountHistory(100);
        this.balance = new BigDecimal(0);
        this.history.append(new AccountTransaction(TransactionType.OPEN, this.balance));
    }

    public BankAccount(Person accountOwner) {
        this(accountOwner.getFirstname() + " " + accountOwner.getLastname(), accountOwner);
    }
    
    public BankAccount deposit(double amount) {
        if (amount<=0) return null;
        BigDecimal d = new BigDecimal(amount);
        balance = balance.add(d);
        this.history.append(new AccountTransaction(TransactionType.DEPOSIT, d));
        return this;

    }
    
    public BankAccount withdraw(double amount) {
        if (amount<=0) return null;
        if (balance.doubleValue()<amount) return null;
        BigDecimal d = new BigDecimal(amount);
        balance = balance.add(d);
        this.history.append(new AccountTransaction(TransactionType.WITHDRAW, d));
        return this;
    }
    
    public BankAccount transferTo(BankAccount to, double amount) {
            if(to == null){
                return null;
            }if(withdraw(amount) == null){
                return null;
            }else{
                withdraw(amount);
                to.deposit(amount);
                BigDecimal d = new BigDecimal(amount);
                balance = balance.add(d);
                this.history.append(new AccountTransaction(TransactionType.TRANSFER_IN, d));
            }
        return this;
    }

    public Person getAccountOwner() { return accountOwner; }

    @Override
    public String toString() {
        return "BankAccount[" + accountNo + ":" + accountName + "=" + balance + ']';
    }
    
}
