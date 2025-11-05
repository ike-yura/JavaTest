import java.math.BigDecimal;
import java.util.Objects;

// 取引（入金・出金など）1件分の情報を保持するクラス
// 不変(immutable)オブジェクトとして設計されており、生成後に内容を変更できない
public class Transaction {

    // ==============================
    // フィールド
    // ==============================

    // 取引の種類（例："DEPOSIT", "WITHDRAW" など）
    private final String m_type;

    // 取引金額（正の値のみ許可）
    private final BigDecimal m_amount;

    // 取引後の残高
    private final BigDecimal m_balanceAfter;

    // メモや備考（例："給与入金", "ATM出金" など）
    private final String m_note;

    // ==============================
    // コンストラクタ
    // ==============================

    // 取引内容を指定してTransactionを生成
    public Transaction(String type, BigDecimal amount, BigDecimal balanceAfter, String note) {

        // 取引種別がnullまたは空文字の場合は例外
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("typeがnull又は空白です");
        }

        // 金額がnull または 0以下なら例外
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amountは正の値である必要があります");
        }

        // 残高がnullなら例外
        if (balanceAfter == null) {
            throw new IllegalArgumentException("balanceAfterはnullです");
        }

        // 備考がnullなら例外
        if (note == null) {
            throw new IllegalArgumentException("noteはnullです");
        }

        // すべてのチェックを通過したら代入（不変のためfinal）
        m_type = type;
        m_amount = amount;
        m_balanceAfter = balanceAfter;
        m_note = note;
    }

    // ==============================
    // Getter
    // ==============================

    // 取引種別を返す（"DEPOSIT" / "WITHDRAW"など）
    public String getType() {
        return m_type;
    }

    // 金額を返す
    public BigDecimal getAmount() {
        return m_amount;
    }

    // 取引後の残高を返す
    public BigDecimal getBalanceAfter() {
        return m_balanceAfter;
    }

    // メモ・備考を返す
    public String getNote() {
        return m_note;
    }

    // ==============================
    // 表示関連
    // ==============================

    // 取引内容を文字列として整形して返す（デバッグ・ログ出力用）
    @Override
    public String toString() {
        return "Transaction(type=" + m_type
                + ", amount=" + m_amount
                + ", balanceAfter=" + m_balanceAfter
                + ", note=" + m_note + ")";
    }

    // ==============================
    // 等価性の定義
    // ==============================

    // すべてのフィールド（type, amount, balanceAfter, note）が等しい場合に同一とみなす
    @Override
    public boolean equals(Object obj) {
        // 同一インスタンス参照ならtrue
        if (this == obj) {
            return true;
        }

        // nullまたは型が異なればfalse
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // キャストして中身を比較
        Transaction other = (Transaction) obj;
        return this.m_type.equals(other.m_type)
                && this.m_amount.equals(other.m_amount)
                && this.m_balanceAfter.equals(other.m_balanceAfter)
                && this.m_note.equals(other.m_note);
    }

    // equalsに対応するhashCode（MapやSetなどで使用される）
    @Override
    public int hashCode() {
        return Objects.hash(m_type, m_amount, m_balanceAfter, m_note);
    }
}
