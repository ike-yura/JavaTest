import java.util.Scanner;

public class BaseTest {
    public static void main(String[] args) {
        int num1 = 10;
        Scanner sc = new Scanner(System.in);

        System.out.println("あなたの名前を教えてください");
        String name = sc.nextLine();

        System.out.println("こんにちは " + name + " さん! " + num1 + " に足す値を入力してください");
        int num2 = sc.nextInt();

        int result = num1 + num2;
        System.out.println("num1 + num2 = " + result + " です");

        sc.close();
    }
}
