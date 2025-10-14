public class Point {
    // メンバ変数
    private final int m_X;
    private final int m_Y;

    // コンストラクタ
    public Point(int x, int y) {
        m_X = x;
        m_Y = y;
    }

    // getter
    public int getX() {
        return m_X;
    }

    public int getY() {
        return m_Y;
    }

    @Override
    public String toString() {
        return "Point(x =" + m_X
                + ",y=" + m_Y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true; // 同一参照なのでtrue
        if (o == null)
            return false;// nullなのでfalse;
        if (!(o instanceof Point))
            return false;// 型が違うのでfalse
        Point other = (Point) o;

        return this.m_X == other.m_X && this.m_Y == other.m_Y;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(m_X);
        result = 31 * result + Integer.hashCode(m_Y);
        return result;
    }
}