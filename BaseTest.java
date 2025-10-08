import java.util.Random;
import java.util.Scanner;

public class BaseTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int m_EnemyHand = 0;
        int m_PlayerHand = 0;
        boolean m_Finish = false;
        System.out.println("じゃんけんをはじめます!じゃーんけーんぽい!");

        while (!m_Finish) {
            System.out.println("出す手を決めてください,0ならグー,1ならチョキ,2ならパー");
            // 0~2までの乱数を入れる(0 = グー: 1 = チョキ: 2 = パー )
            m_EnemyHand = rand.nextInt(3);
            // データの整数チェック
            if (sc.hasNextInt()) {
                m_PlayerHand = sc.nextInt();// プレイヤーの出した手
                System.out.println(
                        "プレイヤーが出した手は" + HandMassege(m_PlayerHand) + "です。敵が出した手は" + HandMassege(m_EnemyHand) + "です。");
                if (m_PlayerHand >= 0 && m_PlayerHand <= 2) {
                    if (m_EnemyHand == m_PlayerHand) {
                        System.out.println("あいこです。もう一回やりましょう");
                        continue;
                    } else if ((m_PlayerHand == 0 && m_EnemyHand == 1) || (m_PlayerHand == 1 && m_EnemyHand == 2)
                            || (m_PlayerHand == 2 && m_EnemyHand == 0)) {
                        System.out.println("あなたの勝ちです！");
                        m_Finish = true;
                    } else {
                        System.out.println("あなたの負けです・・・");
                        m_Finish = true;
                    }
                } else {
                    System.out.println("範囲外の数字です");
                }
            } else {
                System.out.println("整数ではないのでエラーです");
            }
        }
        sc.close();
    }

    static private String HandMassege(int Hand) {
        String l_Massage = "";
        // じゃんけんの手
        if (Hand == 0) {
            l_Massage = "グー";
        } else if (Hand == 1) {
            l_Massage = "チョキ";
        } else {
            l_Massage = "パー";
        }

        return l_Massage;
    }
}
