import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // ① equalsのテスト
        Item item1 = new Item("ID-001", "Pencil", new BigDecimal("100"));
        Item item2 = new Item("ID-001", "Pencil", new BigDecimal("100"));
        System.out.println("item1.equals(item2) = " + item1.equals(item2)); // true になるはず

        // ② hashCodeの確認
        System.out.println("hashCode item1 = " + item1.hashCode());
        System.out.println("hashCode item2 = " + item2.hashCode());

        // ③ HashSetのテスト
        Set<Item> set = new HashSet<>();
        set.add(item1);
        set.add(item2); // 重複扱いになる
        System.out.println("Set size = " + set.size()); // 1 になるはず

        // ④ HashMapのテスト
        Map<Item, Integer> stock = new HashMap<>();
        stock.put(item1, 10);
        stock.put(item2, 20); // 上書きされる
        System.out.println("Map size = " + stock.size()); // 1

        System.out.println(item1);
        System.out.println(item2);

        // Invectory関係
        InventoryItem invent_item = new InventoryItem(item1, 3).increase(3);
        System.out.println(invent_item);
        invent_item = invent_item.decrease(4);
        System.out.println(invent_item);
    }
}
