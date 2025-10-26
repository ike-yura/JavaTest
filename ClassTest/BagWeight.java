import java.math.BigDecimal;
import java.math.RoundingMode;

public class BagWeight {
    // メンバ変数
    private Inventory m_inventory;
    private BigDecimal m_maxWeight;

    // コンストラクタ
    public BagWeight(Inventory inventory, BigDecimal maxWeight) {
        if (maxWeight == null || maxWeight.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("maxWeightは正の値である必要があります");// 例外投げる。
        }

        if (inventory == null) {
            throw new IllegalArgumentException("inventoryがnullです");// 例外投げる。
        }

        if (inventory.getTotalWeight().compareTo(maxWeight) > 0) {
            throw new IllegalArgumentException("初期在庫が重量上限を超えています（maxWeight="
                    + maxWeight + ",totalWeight="
                    + inventory.getTotalWeight() + ")");// 例外投げる。
        }

        m_inventory = inventory;
        m_maxWeight = maxWeight;
    }

    // getter
    public BigDecimal getMaxWeight() {
        return m_maxWeight;
    }

    public BigDecimal getRemainingWeight() {
        return m_maxWeight.subtract(m_inventory.getTotalWeight());
    }

    // 重量をプラスする
    public void add(Item item, int quantity) {
        if (item == null) {
            throw new IllegalArgumentException("itemがnullです");// 例外投げる。
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("quantityは1以上である必要があります");// 例外投げる。
        }

        BigDecimal l_current = m_inventory.getTotalWeight();
        BigDecimal l_addWeight = item.getWeight().multiply(BigDecimal.valueOf(quantity));

        if (l_current.add(l_addWeight).compareTo(m_maxWeight) > 0) {
            throw new IllegalArgumentException(
                    "重量上限を超えるため追加できません（maxWeight=" + m_maxWeight
                            + ", current=" + l_current
                            + ", addWeight=" + l_addWeight + "）");
        }
        m_inventory.add(item, quantity);
    }

    // 重量を減らす
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
        return "BagWeight(max=" + m_maxWeight
                + ",remaining=" + getRemainingWeight().setScale(2, RoundingMode.HALF_UP) + ")";
    }
}
