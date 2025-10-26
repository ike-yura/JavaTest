//Inventoryを内部にもち総戸数が一定上限を超えないように管理するクラス
public class Bag {
    // メンバ変数
    private int m_capacity = 0;
    private Inventory m_inventory;

    // コンストラクタ
    public Bag(int capacity, Inventory inventory) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacityは1以上である必要があります");// 例外投げる。
        }

        if (inventory == null) {
            throw new IllegalArgumentException("inventoryがnullです");// 例外投げる。
        }

        if (inventory.getTotalQuantity() > capacity) {
            throw new IllegalArgumentException("初期在庫が容量を超えています");// 例外投げる。
        }

        m_capacity = capacity;
        m_inventory = inventory;
    }

    // getter
    public int getCapacity() {
        return m_capacity;
    }

    public int getRemainingCapacity() {
        return m_capacity - m_inventory.getTotalQuantity();
    }

    // 容量をプラスする
    public void add(Item item, int quantity) {
        if (item == null) {
            throw new IllegalArgumentException("itemがnullです");// 例外投げる。
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("quantityは1以上である必要があります");// 例外投げる。
        }

        int l_current = m_inventory.getTotalQuantity();

        if (l_current + quantity > m_capacity) {
            throw new IllegalArgumentException(
                    "容量を超えるため追加できません（capacity=" + m_capacity
                            + ", current=" + l_current
                            + ", add=" + quantity + "）");// 例外投げる。
        }

        m_inventory.add(item, quantity);
    }

    // 容量を減らす
    public void remove(Item item, int quantity) {
        if (item == null) {
            throw new IllegalArgumentException("itemがnullです");// 例外投げる。
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("quantityは1以上である必要があります");// 例外投げる。
        }

        m_inventory.remove(item, quantity);
    }

    @Override
    public String toString() {
        return "Bag(capacity=" + m_capacity
                + ",remaining=" + getRemainingCapacity()
                + ",) | items:" + m_inventory.toString() + ")";
    }
}
