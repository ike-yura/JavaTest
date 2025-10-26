import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        // ====== ここまでが既存（容量版 Bag のテスト） ======
        Inventory inv = new Inventory();
        Item apple = new Item("ID-001", "リンゴ", new BigDecimal("120"), new BigDecimal("1.2"));
        Item banana = new Item("ID-002", "バナナ", new BigDecimal("80"), new BigDecimal("0.5"));

        Bag bag = new Bag(5, inv); // 容量5のBag
        System.out.println("初期状態: " + bag);

        // ===== 正常系 =====
        try {
            bag.add(apple, 3); // A×3 => 合計3
            System.out.println("A×3追加後: " + bag);
            bag.add(banana, 2); // B×2 => 合計5
            System.out.println("B×2追加後: " + bag);
        } catch (Exception e) {
            System.out.println("例外: " + e.getMessage());
        }

        // ===== 容量超過 =====
        try {
            bag.add(banana, 1); // 合計6 → 例外
        } catch (Exception e) {
            System.out.println("容量超過テスト → " + e.getMessage());
        }

        // ===== 引数不正 =====
        try {
            bag.add(null, 1);
        } catch (Exception e) {
            System.out.println("nullテスト → " + e.getMessage());
        }

        try {
            bag.add(apple, 0);
        } catch (Exception e) {
            System.out.println("quantity=0テスト → " + e.getMessage());
        }

        // 加算後の重量（容量版でも Inventory の総重量は確認できる）
        System.out.println("合計重量(容量版inv): " + inv.getTotalWeight() + " kg");

        // ===== 正常系（remove） =====
        try {
            bag.remove(apple, 2); // A を2減らす => 合計3
            System.out.println("A×2削除後: " + bag);
            bag.remove(banana, 1); // B を1減らす => 合計2
            System.out.println("B×1削除後: " + bag);
        } catch (Exception e) {
            System.out.println("例外: " + e.getMessage());
        }

        // ===== 削除超過 =====
        try {
            bag.remove(banana, 8); // 在庫割れ → Inventory 側の例外
        } catch (Exception e) {
            System.out.println("削除超過テスト → " + e.getMessage());
        }

        // ===== 引数不正（remove） =====
        try {
            bag.remove(null, 1);
        } catch (Exception e) {
            System.out.println("nullテスト → " + e.getMessage());
        }

        try {
            bag.remove(apple, 0);
        } catch (Exception e) {
            System.out.println("quantity=0テスト → " + e.getMessage());
        }

        // 削除後の重量
        System.out.println("合計重量(容量版inv): " + inv.getTotalWeight() + " kg");

        // ====== ここから重量版 BagWeight のテスト（別在庫で独立検証） ======
        System.out.println("\n===== 重量版 BagWeight テスト開始 =====");
        Inventory invW = new Inventory(); // 重量版は別インベントリで検証
        BagWeight bagW = new BagWeight(invW, new BigDecimal("5.0")); // 上限5.0kg
        System.out.println("初期状態: " + bagW); // BagWeight(max=5.0, remaining=5.0) 期待

        // 正常追加（上限ちょうどまでOK）
        try {
            bagW.add(apple, 2); // 1.2kg ×2 = 2.4kg
            System.out.println("apple×2 追加後: " + bagW); // remaining=2.6

            bagW.add(banana, 5); // 0.5kg ×5 = 2.5kg → 合計 4.9kg
            System.out.println("banana×5 追加後: " + bagW); // remaining=0.1

            bagW.add(banana, 1); // 0.5kg → 合計 5.4kg となり上限超過で例外
        } catch (Exception e) {
            System.out.println("重量上限超過テスト → " + e.getMessage());
        }

        // remove で余裕を戻し、再追加が通ることを確認
        try {
            bagW.remove(banana, 2); // 1.0kg 減少 → remaining が増える
            System.out.println("banana×2 削除後: " + bagW);

            bagW.add(banana, 1); // 0.5kg 追加 → 今度は通るはず
            System.out.println("banana×1 再追加後: " + bagW);
        } catch (Exception e) {
            System.out.println("例外: " + e.getMessage());
        }

        // 引数不正チェック
        try {
            bagW.add(null, 1);
        } catch (Exception e) {
            System.out.println("重量版 nullテスト(add) → " + e.getMessage());
        }

        try {
            bagW.add(apple, 0);
        } catch (Exception e) {
            System.out.println("重量版 quantity=0テスト(add) → " + e.getMessage());
        }

        try {
            bagW.remove(null, 1);
        } catch (Exception e) {
            System.out.println("重量版 nullテスト(remove) → " + e.getMessage());
        }

        try {
            bagW.remove(apple, -1);
        } catch (Exception e) {
            System.out.println("重量版 quantity<0テスト(remove) → " + e.getMessage());
        }

        // 総重量の最終確認
        System.out.println("合計重量(重量版invW): " + invW.getTotalWeight() + " kg");
        System.out.println("最終状態: " + bagW);
    }
}
