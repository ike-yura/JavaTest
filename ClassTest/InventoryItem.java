import java.math.BigDecimal;

// 1つのItem（商品）とその所持数を管理するクラス
// ※このクラス自体は不変（immutable）：一度生成したら内容を変更しない
public final class InventoryItem {

    // ==============================
    // フィールド
    // ==============================

    // 対応する商品情報（Item）
    private final Item m_item;

    // 所持している数量
    private final int m_quantity;

    // ==============================
    // コンストラクタ
    // ==============================

    // Itemと数量を指定してInventoryItemを生成する
    public InventoryItem(Item item, int quantity) {
        // itemがnullなら例外
        if (item == null) {
            throw new IllegalArgumentException("itemがnullです");
        }

        // 数量が0未満なら例外
        if (quantity < 0) {
            throw new IllegalArgumentException("quantityが0未満です");
        }

        // フィールド初期化（不変のためfinal）
        m_item = item;
        m_quantity = quantity;
    }

    // ==============================
    // 数量の増減（不変オブジェクトとして新インスタンスを返す）
    // ==============================

    // 数量を増やした新しいInventoryItemを返す
    public InventoryItem increase(int delta) {
        // deltaが0以下なら例外
        if (delta <= 0) {
            throw new IllegalArgumentException("deltaが0以下です");
        }

        // 新しい数量で新インスタンスを生成して返す
        return new InventoryItem(m_item, m_quantity + delta);
    }

    // 数量を減らした新しいInventoryItemを返す
    public InventoryItem decrease(int delta) {
        // deltaが0以下 または 現在の数量を超える場合は例外
        if (delta <= 0 || delta > m_quantity) {
            throw new IllegalArgumentException("deltaが0以下かdeltaがquantity以上です");
        }

        // 新しい数量で新インスタンスを生成して返す
        return new InventoryItem(m_item, m_quantity - delta);
    }

    // ==============================
    // Getter
    // ==============================

    // 商品情報を返す
    public Item getItem() {
        return m_item;
    }

    // 数量を返す
    public int getQuantity() {
        return m_quantity;
    }

    // 小計金額を返す（単価 × 数量）
    public BigDecimal getSubtotal() {
        return m_item.getUnitPrice().multiply(BigDecimal.valueOf(m_quantity));
    }

    // ==============================
    // 表示・デバッグ用
    // ==============================

    @Override
    public String toString() {
        // 商品ID・名前・単価・数量・小計を1行にまとめて表示
        return "InventoryItem(itemID=" + m_item.getId()
                + ", name=" + m_item.getName()
                + ", item_unitPrice=" + m_item.getUnitPrice()
                + ", quantity=" + m_quantity
                + ", Subtotal=" + getSubtotal() + ")";
    }
}
