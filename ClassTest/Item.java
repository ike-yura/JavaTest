import java.math.BigDecimal;
import java.util.Objects;

public final class Item {
    // メンバ変数
    private final String m_id;
    private final String m_name;
    private final BigDecimal m_unitPrice;

    public Item(String id, String name, BigDecimal unitPrice) {
        // idの処理
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("idが空かnullです");// 例外投げる。
        }

        // nameの処理
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("nameが空かnullです");// 例外投げる。
        }

        // unitPriceの処理
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("unitPriceがnullか0未満です");// 例外投げる。
        }

        m_id = id;
        m_name = name;
        m_unitPrice = unitPrice;
    }

    // getter
    public String getId() {
        return m_id;
    }

    public String getName() {
        return m_name;
    }

    public BigDecimal getUnitPrice() {
        return m_unitPrice;
    }

    @Override
    public String toString() {
        return "Item(id=" + m_id
                + ",name=" + m_name
                + ",unitPrice=" + m_unitPrice + ")";
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
        Item other = (Item) obj;
        // フィールド比較
        return this.m_id.equals(other.m_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_id);
    }
}