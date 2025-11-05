import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// 銀行口座を表すクラス
// 口座名義(owner)、残高(balance)、取引履歴(transactions)を管理する
public class BankAccount {

    // ==============================
    // フィールド
    // ==============================

    // 口座の所有者名（不変）
    private final String m_owner;

    // 現在の残高（金額の計算誤差を防ぐためBigDecimalで管理）
    private BigDecimal m_balance;

    // 取引履歴を保持するリスト（Transactionの一覧）
    private final List<Transaction> m_transactions;

    // ==============================
    // コンストラクタ
    // ==============================

    // 口座名義と初期残高を指定して口座を生成する
    public BankAccount(String owner, BigDecimal initbalance) {
        // 名義のチェック
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("ownerがnullまたは空白です");
        }

        // 初期残高のnullチェック
        if (initbalance == null) {
            throw new IllegalArgumentException("initbalanceがnullです");
        }

        // 初期残高がマイナスの場合は例外
        if (initbalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("initbalanceは0以上である必要があります");
        }

        // 正常な値であればフィールドに代入
        m_owner = owner;
        m_balance = initbalance;

        // 空の取引履歴リストを作成
        m_transactions = new ArrayList<>();
    }

    // ==============================
    // 入金処理
    // ==============================

    // 残高に金額を加算する
    // 成功時はtrue、金額が不正な場合はfalseを返す
    public boolean deposit(BigDecimal amount) {
        // 金額がnullまたは0以下なら入金不可
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        // 新しい残高を計算
        BigDecimal l_newBalance = m_balance.add(amount);

        // 残高を更新
        m_balance = l_newBalance;

        // 取引履歴に追加（入金として記録）
        m_transactions.add(new Transaction("DEPOSIT", amount, l_newBalance, "入金"));

        return true;
    }

    // ==============================
    // 出金処理
    // ==============================

    // 残高から金額を減算する
    // 成功時はtrue、金額不正または残高不足ならfalseを返す
    public boolean withdraw(BigDecimal amount) {
        // 入力チェック：null、0以下、残高超過を防ぐ
        if (amount == null
                || amount.compareTo(BigDecimal.ZERO) <= 0
                || amount.compareTo(m_balance) > 0) {
            return false;
        }

        // 新しい残高を計算
        BigDecimal l_newBalance = m_balance.subtract(amount);

        // 残高を更新
        m_balance = l_newBalance;

        // 取引履歴に追加（出金として記録）
        m_transactions.add(new Transaction("WITHDRAW", amount, l_newBalance, "出金"));

        return true;
    }

    // ==============================
    // Getter
    // ==============================

    // 現在の残高を取得
    public BigDecimal getBalance() {
        return m_balance;
    }

    // 口座名義を取得
    public String getOwner() {
        return m_owner;
    }

    // 取引履歴のリストを取得
    // 外部からリストを書き換えられないように防御的コピーを返す
    public List<Transaction> getTransactions() {
        return new ArrayList<>(m_transactions);
    }

    // ==============================
    // デバッグ・状態表示
    // ==============================

    @Override
    public String toString() {
        // 名義・残高・取引件数をわかりやすく出力
        return "BankAccount(owner=" + m_owner
                + ", balance=" + m_balance
                + ",transactions=" + m_transactions.size() + "件)";
    }
}
