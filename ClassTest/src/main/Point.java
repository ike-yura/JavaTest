// 2次元座標上の点を表すクラス
// X座標・Y座標を持つ不変(immutable)オブジェクト
public class Point {

    // ==============================
    // フィールド
    // ==============================

    // X座標
    private final int m_X;

    // Y座標
    private final int m_Y;

    // ==============================
    // コンストラクタ
    // ==============================

    // X座標とY座標を指定してPointを生成
    public Point(int x, int y) {
        m_X = x;
        m_Y = y;
    }

    // ==============================
    // Getter
    // ==============================

    // X座標を取得
    public int getX() {
        return m_X;
    }

    // Y座標を取得
    public int getY() {
        return m_Y;
    }

    // ==============================
    // 表示関連
    // ==============================

    // 座標情報を文字列で返す（デバッグ・出力用）
    @Override
    public String toString() {
        return "Point(x=" + m_X + ", y=" + m_Y + ")";
    }

    // ==============================
    // 等価性の定義
    // ==============================

    // 2つのPointが同じ座標なら等しいとみなす
    @Override
    public boolean equals(Object o) {
        // 同一インスタンスならtrue
        if (this == o)
            return true;

        // nullならfalse
        if (o == null)
            return false;

        // 型が異なればfalse
        if (!(o instanceof Point))
            return false;

        // キャストして座標値を比較
        Point other = (Point) o;
        return this.m_X == other.m_X && this.m_Y == other.m_Y;
    }

    // equalsと整合性を保つためのhashCode
    // XとYの組み合わせで一意なハッシュ値を生成
    @Override
    public int hashCode() {
        int result = Integer.hashCode(m_X);
        result = 31 * result + Integer.hashCode(m_Y);
        return result;
    }
}
