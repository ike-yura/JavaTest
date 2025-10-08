//import java.util.Scanner;

public class BaseTest {
    public static void main(String[] args) {
        final int MAX_VALUE = 10;
        for (int i = 1; i < MAX_VALUE; i++) {
            for (int j = 1; j < MAX_VALUE; j++) {
                int result = i * j;
                System.out.printf("%2d  × %2d = %2d\n", i, j, result);
            }
            System.out.println();
        }
    }
}
