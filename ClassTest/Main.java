import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // ① 同じ値の比較テスト
        Name n1 = new Name("Ikeuchi", "Yura");
        Name n2 = new Name("Ikeuchi", "Yura");
        System.out.println("n1.equals(n2) = " + n1.equals(n2)); // true になるべき

        // ② hashCodeの一致確認
        System.out.println("hashCode n1 = " + n1.hashCode());
        System.out.println("hashCode n2 = " + n2.hashCode());

        // ③ HashSetに入るか確認（重複が消えるか）
        Set<Name> set = new HashSet<>();
        set.add(n1);
        set.add(n2); // 同じ値なので追加されないはず
        System.out.println("Set size = " + set.size()); // 1になるべき

        // ④ HashMapでキーに使えるか
        Map<Name, String> map = new HashMap<>();
        map.put(n1, "first");
        map.put(n2, "second"); // 上書きされるはず
        System.out.println("Map size = " + map.size()); // 1になるべき

        // ⑤ toString動作確認
        System.out.println(n1.getFullName());
        System.out.println(n1);
    }
}
