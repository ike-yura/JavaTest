import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        Point p3 = new Point(2, 1);

        System.out.println(p1);

        // 2) equals：同値/非同値
        System.out.println("p1.equals(p2) = " + p1.equals(p2)); // true
        System.out.println("p1.equals(p3) = " + p1.equals(p3)); // false

        // 3) 参照比較(==)は“同一インスタンスか”の判定
        System.out.println("p1 == p2 ? " + (p1 == p2)); // false（別インスタンス）

        // 4) Setでの重複判定（equals/hashCode の整合性テスト）
        Set<Point> set = new HashSet<>();
        set.add(p1);
        set.add(p2); // 同値なので追加されないはず
        set.add(p3);
        System.out.println("set size = " + set.size()); // 2 になるのが正解

        // 5) Mapのキー検証（同値キーでgetできるか）
        Map<Point, String> map = new HashMap<>();
        map.put(p1, "hello");
        System.out.println(map.get(p2)); // "hello"（同値キーで取得できる）

    }
}