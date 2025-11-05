// 長方形（Rectangle）を表すクラス
// 幅・高さ・面積・周囲長などを管理・計算する
public class Rectangle {

    // ==============================
    // 定数
    // ==============================

    // 幅・高さの最小値（1未満を防ぐための下限値）
    private static final int MIN_SIZE = 1;

    // ==============================
    // フィールド（メンバ変数）
    // ==============================

    // 幅（width）
    private int m_Width = 0;

    // 高さ（height）
    private int m_Height = 0;

    // ==============================
    // コンストラクタ
    // ==============================

    // 幅と高さを指定して長方形を生成
    // ただし指定値が1未満の場合はMIN_SIZE（1）に補正される
    public Rectangle(int width, int height) {
        m_Width = atLeast(MIN_SIZE, width);
        m_Height = atLeast(MIN_SIZE, height);
    }

    // 正方形（幅＝高さ）を生成する静的メソッド
    // new Rectangle(size, size) の簡易版
    public static Rectangle fromSquare(int size) {
        return new Rectangle(size, size);
    }

    // ==============================
    // 計算メソッド
    // ==============================

    // 面積を返す（幅×高さ）
    public int getArea() {
        return m_Width * m_Height;
    }

    // 周長を返す（2×(幅＋高さ)）
    public int getPerimeter() {
        return 2 * (m_Width + m_Height);
    }

    // ==============================
    // 表示関連
    // ==============================

    // 長方形の情報をまとめて文字列化（デバッグや出力用）
    @Override
    public String toString() {
        return "Rectangle(width=" + m_Width
                + ", height=" + m_Height
                + ", area=" + getArea()
                + ", perimeter=" + getPerimeter() + ")";
    }

    // ==============================
    // Getter / Setter
    // ==============================

    public int getHeight() {
        return m_Height;
    }

    public int getWidth() {
        return m_Width;
    }

    // 幅を設定する。1以上なら更新してtrue、1未満ならfalseを返す
    public boolean setWidth(int value) {
        if (value >= 1) {
            m_Width = value;
            return true;
        } else {
            return false;
        }
    }

    // 高さを設定する。1以上なら更新してtrue、1未満ならfalseを返す
    public boolean setHeight(int value) {
        if (value >= 1) {
            m_Height = value;
            return true;
        } else {
            return false;
        }
    }

    // ==============================
    // 内部ユーティリティ
    // ==============================

    // 指定した値vがlimit未満ならlimitを返す（下限補正）
    private static int atLeast(int limit, int v) {
        return Math.max(limit, v);
    }
}
