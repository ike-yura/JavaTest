import java.math.BigDecimal;

//アイテムを所持個数を管理するクラス
public final class InventoryItem {
    // メンバ変数
    private final Item m_item;
    private final int m_quantity;

    // コンストラクタ
    public InventoryItem(Item item, int quantity) {
        // itemの処理
        if (item == null) {
            throw new IllegalArgumentException("itemがnullです");// 例外投げる。
        }

        // quantityの処理
        if (quantity < 0) {
            throw new IllegalArgumentException("quantityが0未満です");// 例外投げる。
        }

        m_item = item;
        m_quantity = quantity;
    }

    // 加算
    public InventoryItem increase(int delta) {
        if (delta <= 0) {
            throw new IllegalArgumentException("deltaが0以下です");// 例外投げる。
        }
        return new InventoryItem(m_item, m_quantity + delta);
    }

    // 減算
    public InventoryItem decrease(int delta) {
        if (delta <= 0 || delta > m_quantity) {
            throw new IllegalArgumentException("deltaが0以下かdeltaがquantity以上です");// 例外投げる。
        }
        return new InventoryItem(m_item, m_quantity - delta);
    }

    // getter
    public Item getItem() {
        return m_item;
    }

    public int getQuantity() {
        return m_quantity;
    }

    public BigDecimal getSubtotal() {
        return m_item.getUnitPrice().multiply(BigDecimal.valueOf(m_quantity));
    }

    @Override
    public String toString() {
        return "InventoryItem(itemID=" + m_item.getId()
                + ",name=" + m_item.getName()
                + ",item_unitPrice=" + m_item.getUnitPrice()
                + ",quantity=" + m_quantity
                + ",Subtotal=" + getSubtotal() + ")";
    }
}