import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    // メンバ変数
    private final String m_owner;
    private BigDecimal m_balance;
    private final List<Transaction> m_transactions;

    // コンストラクタ
    public BankAccount(String owner, BigDecimal initbalance) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("ownerがnullまたは空白です");
        }
        if (initbalance == null) {
            throw new IllegalArgumentException("initbalanceがnullです");
        }
        if (initbalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("initbalanceは0以上である必要があります");
        }

        m_owner = owner;
        m_balance = initbalance;
        m_transactions = new ArrayList<>();
    }

    // 残高に加算する
    public boolean deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        BigDecimal l_newBalance = m_balance.add(amount);
        m_balance = l_newBalance;
        m_transactions.add(new Transaction("DEPOSIT", amount, l_newBalance, "入金"));
        return true;
    }

    // 残高から減算する
    public boolean withdraw(BigDecimal amount) {
        if (amount == null
                || amount.compareTo(BigDecimal.ZERO) <= 0
                || amount.compareTo(m_balance) > 0) {
            return false;
        }
        BigDecimal l_newBalance = m_balance.subtract(amount);
        m_balance = l_newBalance;
        m_transactions.add(new Transaction("WITHDRAW", amount, l_newBalance, "出金"));
        return true;
    }

    // getter setter
    public BigDecimal getBalance() {
        return m_balance;
    }

    public String getOwner() {
        return m_owner;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(m_transactions);
    }

    @Override
    public String toString() {
        return "BankAccount(owner=" + m_owner
                + ", balance=" + m_balance
                + ",transactions=" + m_transactions.size() + "件)";
    }
}