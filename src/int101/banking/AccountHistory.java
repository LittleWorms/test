package int101.banking;

public class AccountHistory {
    private final AccountTransaction history[];
    private int count;

    public AccountHistory(int size) {
        history = new AccountTransaction[size>0 ? size :100];
    }
    
    public AccountHistory append(AccountTransaction trx) {
        if (count < history.length) {
            history[count++] = trx;
            return this;
        }
        return null;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < count; i++){
            sb.append(history[i].toString()).append("\n\t");
            }    
        return sb.toString();
    }

}
