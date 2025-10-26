import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        // ===== 準備 =====
        Inventory inv = new Inventory();
        Item apple = new Item("ID-001", "リンゴ", new BigDecimal("120"));
        Item banana = new Item("ID-002", "バナナ", new BigDecimal("80"));

        Bag bag = new Bag(5, inv); // 容量5のBag
        System.out.println("初期状態: " + bag);

        // ===== 正常系 =====
        try {
            bag.add(apple, 3);
            System.out.println("A×3追加後: " + bag);
            bag.add(banana, 2);
            System.out.println("B×2追加後: " + bag);
        } catch (Exception e) {
            System.out.println("例外: " + e.getMessage());
        }

        // ===== 容量超過 =====
        try {
            bag.add(banana, 1);
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

        // ===== 正常系 =====
        try {
            bag.remove(apple, 2);
            System.out.println("A×2削除後: " + bag);
            bag.remove(banana, 1);
            System.out.println("B×1削除後: " + bag);
        } catch (Exception e) {
            System.out.println("例外: " + e.getMessage());
        }

        // ===== 削除超過 =====
        try {
            bag.remove(banana, 8);
        } catch (Exception e) {
            System.out.println("削除超過テスト → " + e.getMessage());
        }

        // ===== 引数不正 =====
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

    }
}
