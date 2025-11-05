import java.math.BigDecimal;
import java.util.Objects;

// 商品1つ分の情報（ID・名前・単価・重さ）を保持するクラス
// 不変(immutable)クラスとして設計されており、一度生成したら内容を変更しない
public final class Item {

    // ==============================
    // フィールド
    // ==============================

    // 商品ID（ユニーク識別子）
    private final String m_id;

    // 商品名
    private final String m_name;

    // 単価（1個あたりの価格）
    private final BigDecimal m_unitPrice;

    // 重量（1個あたりの重さ）
    private final BigDecimal m_weight;

    // ==============================
    // コンストラクタ
    // ==============================

    // 商品ID、名前、単価、重量を指定してItemを生成する
    public Item(String id, String name, BigDecimal unitPrice, BigDecimal weight) {

        // idがnullまたは空文字の場合はエラー
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("idが空かnullです");
        }

        // nameがnullまたは空文字の場合はエラー
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("nameが空かnullです");
        }

        // 単価がnullまたは負の値の場合はエラー
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("unitPriceがnullか0未満です");
        }

        // 重量がnullまたは負の値の場合はエラー
        if (weight == null || weight.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("weightがnullか0未満です");
        }

        // すべてのバリデーションを通過したらフィールドに代入
        m_id = id;
        m_name = name;
        m_unitPrice = unitPrice;
        m_weight = weight;
    }

    // ==============================
    // Getter
    // ==============================

    public String getId() {
        return m_id;
    }

    public String getName() {
        return m_name;
    }

    public BigDecimal getUnitPrice() {
        return m_unitPrice;
    }

    public BigDecimal getWeight() {
        return m_weight;
    }

    // ==============================
    // 表示関連
    // ==============================

    // 商品の情報をまとめて文字列化（デバッグやログ出力用）
    @Override
    public String toString() {
        return "Item(id=" + m_id
                + ", name=" + m_name
                + ", unitPrice=" + m_unitPrice
                + ", weight=" + m_weight + ")";
    }

    // ==============================
    // 等価性の定義
    // ==============================

    // 同じ商品IDを持つItem同士を「同一」とみなす
    @Override
    public boolean equals(Object obj) {
        // 参照が同じならtrue
        if (this == obj) {
            return true;
        }

        // nullまたは型が異なる場合はfalse
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // キャストしてIDを比較
        Item other = (Item) obj;
        return this.m_id.equals(other.m_id);
    }

    // equalsに対応するhashCode（MapやSetでの利用に必須）
    @Override
    public int hashCode() {
        return Objects.hash(m_id);
    }
}
