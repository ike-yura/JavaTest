public class Main {
    public static void main(String[] args) {
        //
        Counter c1 = new Counter(0);
        Counter c2 = new Counter(5);

        // 加算をしている
        c1.increment(); // 1
        c1.increment(); // 2
        // 表示
        System.out.println(c1.toString()); // 2
        // 値リセット
        c1.reset(); // 0
        // 表示
        System.out.println(c1.toString()); // 0
        System.out.println(c2.toString()); // 「現在のカウント：5」
    }
}