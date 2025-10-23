import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        // 準備：商品2種
        Item itemA = new Item("ID-001", "Pencil", new BigDecimal("100")); // 単価100
        Item itemB = new Item("ID-002", "Notebook", new BigDecimal("250")); // 単価250

        Inventory inv = new Inventory();

        System.out.println("=== 新規追加 ===");
        inv.add(itemA, 3); // 3個
        dump(inv, "Aを3個追加後"); // 期待: qty(A)=3, total=300, totalQty=3

        System.out.println("\n=== 既存加算 ===");
        inv.add(itemA, 2); // +2 → 合計5
        dump(inv, "Aをさらに2個追加後"); // 期待: qty(A)=5, total=500, totalQty=5

        System.out.println("\n=== 別商品追加 ===");
        inv.add(itemB, 1); // Bを1個
        dump(inv, "Bを1個追加後"); // 期待: qty(A)=5, qty(B)=1, total=500+250=750, totalQty=6

        System.out.println("\n=== 部分減算 ===");
        inv.remove(itemA, 1); // A: 5→4
        dump(inv, "Aを1個減算後"); // 期待: qty(A)=4, qty(B)=1, total=400+250=650, totalQty=5

        System.out.println("\n=== ちょうど0で削除 ===");
        inv.remove(itemA, 4); // A: 4→0 → エントリ削除
        dump(inv, "Aを4個減算（0→削除）後"); // 期待: qty(A)=0, qty(B)=1, total=250, totalQty=1

        System.out.println("\n=== 例外系テスト：未登録を減算 ===");
        try {
            inv.remove(itemA, 1); // 既に削除済みのAを減算 → 例外期待
            System.out.println("NG: 例外が出るべきケースで出ませんでした");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: 期待どおり例外: " + e.getMessage());
        }

        System.out.println("\n=== 例外系テスト：在庫割れ ===");
        try {
            inv.remove(itemB, 2); // Bは1個しかない → 例外期待
            System.out.println("NG: 例外が出るべきケースで出ませんでした");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: 期待どおり例外: " + e.getMessage());
        }

        System.out.println("\n=== 例外系テスト：不正な引数 ===");
        try {
            inv.add(itemB, 0); // 0は不正
            System.out.println("NG: 例外が出るべきケースで出ませんでした");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: 期待どおり例外: " + e.getMessage());
        }
        try {
            inv.remove(null, 1); // nullは不正
            System.out.println("NG: 例外が出るべきケースで出ませんでした");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: 期待どおり例外: " + e.getMessage());
        }

        System.out.println("\n=== 最終状態 ===");
        dump(inv, "最終");
    }

    private static void dump(Inventory inv, String label) {
        System.out.println("[ " + label + " ]");
        System.out.println(inv.toString());
        System.out.println("TotalQuantity=" + inv.getTotalQuantity());
        // 任意：個別数量を直接見る例（A/Bが登録されているかに依存）
        // System.out.println("qty(A)=" + inv.getQuantity(itemA));
        // System.out.println("qty(B)=" + inv.getQuantity(itemB));
    }
}
