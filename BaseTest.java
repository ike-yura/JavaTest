import java.util.Scanner;

public class BaseTest {
    public static void main(String[] args) {
        int num1 = 10;
        Scanner sc1 = new Scanner(System.in);
        System.out.println(num1 + "に足す値を入力してください");
        int num2 = sc1.nextInt();
        int result = num1 + num2;
        System.out.println("num + num2 = " + result + "です");
        sc1.close();
    }
}
