import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

//複数のInventoryItemを管理するクラス
public class Inventory {
    // メンバ変数
    private final Map<String, InventoryItem> m_items = new HashMap<>();

    // コンストラクタ
    public Inventory() {

    }

    // getter
    // 個数を返す
    public int getQuantity(Item item) {
        if (item == null) {
            return 0;
        }

        InventoryItem l_found = m_items.get(item.getId());

        if (l_found == null) {
            return 0;
        }

        return l_found.getQuantity();
    }

    // 合計金額を返す
    public BigDecimal getTotalAmount() {
        BigDecimal l_total = BigDecimal.ZERO;

        for (InventoryItem item : m_items.values()) {
            l_total = l_total.add(item.getSubtotal());
        }

        return l_total;
    }

    // Itemの加算
    public void add(Item item, int quantity) {
        if (item == null) {
            throw new IllegalArgumentException("itemがnullです");// 例外投げる。
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantityが0以下です");// 例外投げる。
        }

        // IDを取得する
        String l_id = item.getId();
        // 登録されているアイテム欄から一致を探す。
        InventoryItem l_current = m_items.get(l_id);

        // 登録の有無を判定する
        if (l_current == null) {// 格納されていないので、登録する
            m_items.put(l_id, new InventoryItem(item, quantity));
        } else {// 格納されているので、一致するものにquantityを加算する
            InventoryItem updated = l_current.increase(quantity);
            m_items.put(l_id, updated);
        }
    }

    // アイテムの減算
    public void remove(Item item, int quantity) {
        if (item == null) {
            throw new IllegalArgumentException("itemがnullです");// 例外投げる。
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantityが0以下です");// 例外投げる。
        }

        // IDを取得する
        String l_id = item.getId();
        // 登録されているアイテム欄から一致を探す。
        InventoryItem l_current = m_items.get(l_id);

        if (l_current == null) {
            throw new IllegalArgumentException("在庫が存在しません");// 例外投げる。
        }

        // 減らす在庫が現在庫を上回っている場合
        if (quantity > l_current.getQuantity()) {
            throw new IllegalArgumentException("在庫数を超えて減らせません");// 例外投げる。
        }
        // ちょうどゼロになる場合はmapから消す
        else if (quantity == l_current.getQuantity()) {
            m_items.remove(l_id);
        } else {// 在庫を減らす
            InventoryItem updated = l_current.decrease(quantity);
            m_items.put(l_id, updated);
        }
    }

    // トータルの在庫数を計算
    public int getTotalQuantity() {
        int l_total = 0;

        for (InventoryItem item : m_items.values()) {
            l_total += item.getQuantity();
        }

        return l_total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventory{\n");
        for (InventoryItem item : m_items.values()) {
            sb.append("  ");
            sb.append(item.getItem().getId() + ",(name=" + item.getItem().getName()
                    + ", qty=" + item.getQuantity()
                    + ", subtotal=" + item.getSubtotal() + ")");
            sb.append("\n");
        }
        sb.append("}\n");
        sb.append("TotalAmount=" + getTotalAmount());
        return sb.toString();
    }
}
