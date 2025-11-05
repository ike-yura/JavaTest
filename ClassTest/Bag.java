// Inventoryを内部に持ち、総個数が上限(capacity)を超えないように管理するクラス
public class Bag {

    // ==============================
    // フィールド
    // ==============================

    // バッグの最大容量（保持できるアイテム数の上限）
    private int m_capacity = 0;

    // バッグ内部の在庫情報（Inventoryクラスに委譲）
    private Inventory m_inventory;

    // ==============================
    // コンストラクタ
    // ==============================

    // 指定された容量と初期在庫でBagを生成
    public Bag(int capacity, Inventory inventory) {
        // 容量が1未満なら例外を投げる
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacityは1以上である必要があります");
        }

        // 在庫がnullなら例外
        if (inventory == null) {
            throw new IllegalArgumentException("inventoryがnullです");
        }

        // 初期在庫がすでに容量を超えている場合は例外
        if (inventory.getTotalQuantity() > capacity) {
            throw new IllegalArgumentException("初期在庫が容量を超えています");
        }

        // フィールドに代入
        m_capacity = capacity;
        m_inventory = inventory;
    }

    // ==============================
    // Getter
    // ==============================

    // バッグの最大容量を取得
    public int getCapacity() {
        return m_capacity;
    }

    // 現在の残り容量を計算して返す
    public int getRemainingCapacity() {
        return m_capacity - m_inventory.getTotalQuantity();
    }

    // ==============================
    // アイテム追加処理
    // ==============================

    // バッグにアイテムを追加する（容量制限あり）
    public void add(Item item, int quantity) {
        // 引数チェック
        if (item == null) {
            throw new IllegalArgumentException("itemがnullです");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("quantityは1以上である必要があります");
        }

        // 現在の総個数を取得
        int l_current = m_inventory.getTotalQuantity();

        // 追加後の合計が容量を超える場合は例外
        if (l_current + quantity > m_capacity) {
            throw new IllegalArgumentException(
                    "容量を超えるため追加できません（capacity=" + m_capacity
                            + ", current=" + l_current
                            + ", add=" + quantity + "）");
        }

        // Inventory側のaddを呼び出して在庫に反映
        m_inventory.add(item, quantity);
    }

    // ==============================
    // アイテム削除処理
    // ==============================

    // バッグから指定数量のアイテムを削除する
    public void remove(Item item, int quantity) {
        // 引数チェック
        if (item == null) {
            throw new IllegalArgumentException("itemがnullです");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("quantityは1以上である必要があります");
        }

        // Inventoryに削除を委譲（容量チェックは不要）
        m_inventory.remove(item, quantity);
    }

    // ==============================
    // デバッグ・状態表示
    // ==============================

    @Override
    public String toString() {
        // バッグの容量・残り容量・在庫情報を文字列化
        return "Bag(capacity=" + m_capacity
                + ",remaining=" + getRemainingCapacity()
                + ",) | items:" + m_inventory.toString() + ")";
    }
}
