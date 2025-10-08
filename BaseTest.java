import java.util.Random;
import java.util.Scanner;

public class BaseTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m_Answer = 0;
        boolean m_Check = false;
        Random rand = new Random();

        final int WIDE_DISTANCE = 50;
        final int SMALL_DISTANCE = 20;
        // 0~100までの乱数を入れる
        int m_RandNum = rand.nextInt(101);
        int m_PlayCount = 0;
        // 数字当てるまでは無限ループ
        while (!m_Check) {
            System.out.println("こんにちは! 0 ~ 100の値を入力してください!");
            m_Answer = sc.nextInt();
            int l_Distance = Math.abs(m_RandNum - m_Answer);
            m_PlayCount++;
            // 数字が当たっていれば終わり
            if (l_Distance == 0) {
                m_Check = true;
            } else {
                if (m_Answer < m_RandNum) {
                    if (l_Distance < SMALL_DISTANCE) {
                        System.out.println("答えの数字はもう少し大きいよ!");
                    } else if (l_Distance < WIDE_DISTANCE) {
                        System.out.println("答えの数字はまだ大きいよ!");
                    } else {
                        System.out.println("答えの数字は全然大きいよ!");
                    }
                } else {
                    if (l_Distance < SMALL_DISTANCE) {
                        System.out.println("答えの数字はもう少し小さいよ!");
                    } else if (l_Distance < WIDE_DISTANCE) {
                        System.out.println("答えの数字はまだ小さいよ!");
                    } else {
                        System.out.println("答えの数字は全然小さいよ!");
                    }
                }
            }
            // 5回目からはヒントをだす
            if (m_PlayCount > 4) {
                System.out.println("ヒントです!答えとの差は" + l_Distance + "です!");
            }
        }
        // プレイ回数を出す
        System.out.println("お疲れ様でした!プレイ回数は" + m_PlayCount + "です!");
        sc.close();
    }
}
