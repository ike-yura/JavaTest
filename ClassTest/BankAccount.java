public class BankAccount {
    // メンバ変数
    private final String m_Owner;
    private long m_Balance = 0;

    // コンストラクタ
    public BankAccount(String owner) {
        // ownerのnullチェック
        if (owner == null) {
            m_Owner = "";
        } else {
            m_Owner = owner;
        }
    }

    // コンストラクタ
    public BankAccount(String owner, long initbalance) {
        // ownerのnullチェック
        if (owner == null) {
            m_Owner = "";
        } else {
            m_Owner = owner;
        }

        if (initbalance < 0) {
            m_Balance = 0;
        } else {
            m_Balance = initbalance;
        }
    }

    // 残高に加算する
    public boolean deposit(long amont) {
        if (amont <= 0) {
            return false;
        } else {
            m_Balance += amont;
            return true;
        }
    }

    // 残高から減算する
    public boolean withdraw(long amont) {
        if (amont <= 0 || amont > m_Balance) {
            return false;
        } else {
            m_Balance -= amont;
            return true;
        }
    }

    // getter setter
    public long getBalance() {
        return m_Balance;
    }

    public String getOwner() {
        return m_Owner;
    }

    @Override
    public String toString() {
        return "BankAccount(owner=" + m_Owner + ", balance=" + m_Balance + ")";
    }
}