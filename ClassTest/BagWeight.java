import java.math.BigDecimal;
import java.math.RoundingMode;

// Inventoryを内部に持ち、総重量が上限(maxWeight)を超えないように管理するクラス
public class BagWeight {

    // ==============================
    // フィールド
    // ==============================

    // バッグ内部の在庫情報（Inventoryクラスに委譲）
    private Inventory m_inventory;

    // バッグ全体の最大重量（これを超える追加は禁止）
    private BigDecimal m_maxWeight;

    // ==============================
    // コンストラクタ
    // ==============================

    // 在庫と最大重量を指定してBagWeightを生成
    public BagWeight(Inventory inventory, BigDecimal maxWeight) {
        // maxWeightがnullまたは0以下の場合は例外
        if (maxWeight == null || maxWeight.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("maxWeightは正の値である必要があります");
        }

        // inventoryがnullなら例外
        if (inventory == null) {
            throw new IllegalArgumentException("inventoryがnullです");
        }

        // 初期在庫の総重量が上限を超えている場合は例外
        if (inventory.getTotalWeight().compareTo(maxWeight) > 0) {
            throw new IllegalArgumentException("初期在庫が重量上限を超えています（maxWeight="
                    + maxWeight + ",totalWeight="
                    + inventory.getTotalWeight() + ")");
        }

        // フィールドに代入
        m_inventory = inventory;
        m_maxWeight = maxWeight;
    }

    // ==============================
    // Getter
    // ==============================

    // バッグの最大重量を取得
    public BigDecimal getMaxWeight() {
        return m_maxWeight;
    }

    // 残りの重量許容量を計算して返す
    public BigDecimal getRemainingWeight() {
        // maxWeight から現在の総重量を引いて求める
        return m_maxWeight.subtract(m_inventory.getTotalWeight());
    }

    // ==============================
    // アイテム追加処理（重量チェックあり）
    // ==============================

    // バッグにアイテムを追加する（追加後の重量が上限を超える場合は例外）
    public void add(Item item, int quantity) {
        // 引数チェック
        if (item == null) {
            throw new IllegalArgumentException("itemがnullです");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("quantityは1以上である必要があります");
        }

        // 現在の総重量を取得
        BigDecimal l_current = m_inventory.getTotalWeight();

        // 追加予定の重量 = 単体重量 × 個数
        BigDecimal l_addWeight = item.getWeight().multiply(BigDecimal.valueOf(quantity));

        // 追加後の総重量が上限を超える場合は例外をスロー
        if (l_current.add(l_addWeight).compareTo(m_maxWeight) > 0) {
            throw new IllegalArgumentException(
                    "重量上限を超えるため追加できません（maxWeight=" + m_maxWeight
                            + ", current=" + l_current
                            + ", addWeight=" + l_addWeight + "）");
        }

        // 問題なければInventoryに追加を委譲
        m_inventory.add(item, quantity);
    }

    // ==============================
    // アイテム削除処理
    // ==============================

    // バッグから指定数量のアイテムを削除する（重量チェック不要）
    public void remove(Item item, int quantity) {
        // 引数チェック
        if (item == null) {
            throw new IllegalArgumentException("itemがnullです");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("quantityは1以上である必要があります");
        }

        // Inventory側に削除を委譲
        m_inventory.remove(item, quantity);
    }

    // ==============================
    // デバッグ・状態表示
    // ==============================

    @Override
    public String toString() {
        // バッグの上限・残り重量を少数第2位まで表示
        return "BagWeight(max=" + m_maxWeight
                + ",remaining=" + getRemainingWeight().setScale(2, RoundingMode.HALF_UP) + ")";
    }
}
