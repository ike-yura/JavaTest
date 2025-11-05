import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 口座作成（初期残高 1000）
        BankAccount account = new BankAccount("山田太郎", new BigDecimal("1000"));
        System.out.println("初期状態: " + account);

        // ====== 入金テスト ======
        System.out.println("\n--- depositテスト ---");

        boolean d1 = account.deposit(new BigDecimal("500")); // 正常
        System.out.println("deposit 500 → 結果: " + d1 + " / 現在: " + account);

        boolean d2 = account.deposit(new BigDecimal("-100")); // 不正（マイナス）
        System.out.println("deposit -100 → 結果: " + d2 + " / 現在: " + account);

        boolean d3 = account.deposit(BigDecimal.ZERO); // 不正（0）
        System.out.println("deposit 0 → 結果: " + d3 + " / 現在: " + account);

        // ====== 出金テスト ======
        System.out.println("\n--- withdrawテスト ---");

        boolean w1 = account.withdraw(new BigDecimal("300")); // 正常
        System.out.println("withdraw 300 → 結果: " + w1 + " / 現在: " + account);

        boolean w2 = account.withdraw(new BigDecimal("2000")); // 残高不足
        System.out.println("withdraw 2000 → 結果: " + w2 + " / 現在: " + account);

        boolean w3 = account.withdraw(new BigDecimal("-50")); // 不正（マイナス）
        System.out.println("withdraw -50 → 結果: " + w3 + " / 現在: " + account);

        // ====== 取引履歴の表示 ======
        System.out.println("\n--- 取引履歴 ---");
        List<Transaction> history = account.getTransactions();
        for (Transaction t : history) {
            System.out.println(t);
        }

        System.out.println("\n最終状態: " + account);
    }
}
