public class Rectangle {
    private static final int MIN_SIZE = 1;
    // メンバ変数
    private int m_Width = 0;// 幅
    private int m_Height = 0;// 高さ

    // コンストラクタ
    public Rectangle(int width, int height) {
        m_Width = atLeast(MIN_SIZE, width);
        m_Height = atLeast(MIN_SIZE, height);
    }

    public static Rectangle fromSquare(int size) {
        return new Rectangle(size, size); // 新しい長方形を作って返す
    }

    // 面積
    public int getArea() {
        return m_Width * m_Height;
    }

    // 周長
    public int getPerimeter() {
        return 2 * (m_Width + m_Height);
    }

    @Override
    public String toString() {
        return "Rectangle(width=" + m_Width
                + ", height=" + m_Height
                + ", area=" + getArea()
                + ", perimeter=" + getPerimeter() + ")";
    }

    // getter setter
    public int getHeight() {
        return m_Height;
    }

    public int getWidth() {
        return m_Width;
    }

    public boolean setWidth(int value) {
        if (value >= 1) {
            m_Width = value;
            return true;
        } else {
            return false;
        }
    }

    public boolean setHeight(int value) {
        if (value >= 1) {
            m_Height = value;
            return true;
        } else {
            return false;
        }
    }

    private static int atLeast(int limit, int v) {
        return Math.max(limit, v);
    }
}