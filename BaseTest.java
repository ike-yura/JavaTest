import java.util.Random;
import java.util.Scanner;

public class BaseTest {

    private static final int WIDE_DISTANCE = 50;
    private static final int SMALL_DISTANCE = 20;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m_Answer = 0;
        boolean m_Check = false;
        Random rand = new Random();

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
                // ヒントを与える
                boolean l_IsLow = (m_Answer < m_RandNum);
                System.out.println(HitMassage(l_Distance, l_IsLow));
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

    static private String HitMassage(int distance, boolean isLow) {
        String dir = isLow ? "大きい" : "小さい"; // 回答の大きさで決める
        if (distance < SMALL_DISTANCE) {
            return "答えの数字は、もう少し" + dir + "よ!";
        } else if (distance < WIDE_DISTANCE) {
            return "答えの数字は、まだ" + dir + "よ!";
        } else {
            return "答えの数字は、全然" + dir + "よ!";
        }
    }
}
