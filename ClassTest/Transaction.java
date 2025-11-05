import java.math.BigDecimal;
import java.util.Objects;

public class Transaction {
    // メンバ変数
    private final String m_type;
    private final BigDecimal m_amount;
    private final BigDecimal m_balanceAfter;
    private final String m_note;

    // コンストラクタ
    public Transaction(String type, BigDecimal amount, BigDecimal balanceAfter, String note) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("typeがnull又は空白です");// 例外投げる。
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amountは正の値である必要があります");// 例外投げる。
        }

        if (balanceAfter == null) {
            throw new IllegalArgumentException("balanceAfterはnullです");// 例外投げる。
        }

        if (note == null) {
            throw new IllegalArgumentException("noteはnullです");// 例外投げる。
        }

        m_type = type;
        m_amount = amount;
        m_balanceAfter = balanceAfter;
        m_note = note;
    }

    // ゲッター
    public String getType() {
        return m_type;
    }

    public BigDecimal getAmount() {
        return m_amount;
    }

    public BigDecimal getBalanceAfter() {
        return m_balanceAfter;
    }

    public String getNote() {
        return m_note;
    }

    @Override
    public String toString() {
        return "Transaction(type=" + m_type
                + ",amount=" + m_amount
                + ",balanceAfter=" + m_balanceAfter
                + ",note=" + m_note + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;// 同一参照なのでtrue
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;// objがnullまたは型が違うのでfalse
        }

        // キャスト
        Transaction other = (Transaction) obj;
        // フィールド比較
        return this.m_type.equals(other.m_type)
                && this.m_amount.equals(other.m_amount)
                && this.m_balanceAfter.equals(other.m_balanceAfter)
                && this.m_note.equals(other.m_note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_type, m_amount, m_balanceAfter, m_note);
    }
}
