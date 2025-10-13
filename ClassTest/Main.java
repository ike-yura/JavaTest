public class Main {
    public static void main(String[] args) {
        BankAccount bank_Account = new BankAccount("ike");
        BankAccount bank_Account2 = new BankAccount("mudo", 200);

        // まず加算する
        bank_Account.deposit(300);
        bank_Account2.deposit(2000);
        // 減算する
        bank_Account.withdraw(0);
        bank_Account2.withdraw(1000);

        System.out.println(bank_Account); // toString() が自動で呼ばれる
        System.out.println(bank_Account2);
    }
}