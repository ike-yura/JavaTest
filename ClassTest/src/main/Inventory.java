import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

// 複数のInventoryItemを管理するクラス
// 「どのItemを何個持っているか」をMapで管理し、金額・数量・重量などを集計する
public class Inventory {

    // ==============================
    // フィールド
    // ==============================

    // ItemのID(String) をキー、在庫1行分(InventoryItem)を値とするマップ
    // 例: "ID-001" -> InventoryItem(リンゴ, 数量3, 小計360)
    private final Map<String, InventoryItem> m_items = new HashMap<>();

    // ==============================
    // コンストラクタ
    // ==============================

    // 空の在庫を持つInventoryを生成
    public Inventory() {
        // 特に初期処理はなし。m_itemsはフィールド宣言時に初期化済み。
    }

    // ==============================
    // 在庫数取得関連
    // ==============================

    // 指定したItemの在庫個数を返す
    // Itemがnull、または在庫に存在しない場合は0を返す
    public int getQuantity(Item item) {
        if (item == null) {
            return 0;
        }

        // ItemのIDをキーに、在庫情報を取得
        InventoryItem l_found = m_items.get(item.getId());

        // 登録されていない場合は0
        if (l_found == null) {
            return 0;
        }

        // 見つかった在庫の数量を返す
        return l_found.getQuantity();
    }

    // ==============================
    // 合計金額計算
    // ==============================

    // 在庫全体の合計金額を返す
    public BigDecimal getTotalAmount() {
        BigDecimal l_total = BigDecimal.ZERO;

        // すべてのInventoryItemの小計(subtotal)を足し合わせる
        for (InventoryItem item : m_items.values()) {
            l_total = l_total.add(item.getSubtotal());
        }

        return l_total;
    }

    // ==============================
    // 在庫の追加処理
    // ==============================

    // 指定したItemを指定数量だけ在庫に追加する
    public void add(Item item, int quantity) {
        // 引数チェック
        if (item == null) {
            throw new IllegalArgumentException("itemがnullです");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantityが0以下です");
        }

        // IDを取得する
        String l_id = item.getId();

        // すでに登録されている在庫を取得
        InventoryItem l_current = m_items.get(l_id);

        // 登録の有無を判定する
        if (l_current == null) {
            // まだ在庫がないので、新規にInventoryItemを作成して登録
            m_items.put(l_id, new InventoryItem(item, quantity));
        } else {
            // すでに在庫があるので、数量を増やした新しいInventoryItemを作り直して登録
            InventoryItem updated = l_current.increase(quantity);
            m_items.put(l_id, updated);
        }
    }

    // ==============================
    // 在庫の減算処理
    // ==============================

    // 指定したItemを指定数量だけ在庫から減らす
    public void remove(Item item, int quantity) {
        // 引数チェック
        if (item == null) {
            throw new IllegalArgumentException("itemがnullです");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantityが0以下です");
        }

        // IDを取得する
        String l_id = item.getId();

        // 現在の在庫を取得
        InventoryItem l_current = m_items.get(l_id);

        // 在庫が存在しない場合はエラー
        if (l_current == null) {
            throw new IllegalArgumentException("在庫が存在しません");
        }

        // 減らそうとしている数量が現在の在庫を上回っている場合
        if (quantity > l_current.getQuantity()) {
            throw new IllegalArgumentException("在庫数を超えて減らせません");
        }
        // ちょうどゼロになる場合はmapから削除
        else if (quantity == l_current.getQuantity()) {
            m_items.remove(l_id);
        } else {
            // 在庫を一部だけ減らす場合は、新しいInventoryItemに差し替える
            InventoryItem updated = l_current.decrease(quantity);
            m_items.put(l_id, updated);
        }
    }

    // ==============================
    // 合計数量・重量の集計
    // ==============================

    // 在庫全体の総個数を計算
    public int getTotalQuantity() {
        int l_total = 0;

        // 各InventoryItemの数量を合計する
        for (InventoryItem item : m_items.values()) {
            l_total += item.getQuantity();
        }

        return l_total;
    }

    // 在庫全体の総重量を計算
    public BigDecimal getTotalWeight() {
        BigDecimal l_total = BigDecimal.ZERO;

        // 各InventoryItemについて「1個あたりの重さ × 個数」を合計する
        for (InventoryItem item : m_items.values()) {
            Item l_item = item.getItem();
            BigDecimal l_weight = l_item.getWeight();
            int l_quantity = item.getQuantity();

            // weight × quantity を加算
            l_total = l_total.add(l_weight.multiply(BigDecimal.valueOf(l_quantity)));
        }
        return l_total;
    }

    // ==============================
    // デバッグ・状態表示
    // ==============================

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventory{\n");

        // 各InventoryItemの内容を1行ずつ表示
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
