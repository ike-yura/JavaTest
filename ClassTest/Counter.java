public class Counter {
    private int m_Count = 0;

    Counter(final int count) {
        if (count >= 0) {
            m_Count = count;
        } else {
            m_Count = 0;
        }
    }

    // 加算
    public void increment() {
        m_Count++;
    }

    // リセット
    public void reset() {
        m_Count = 0;
    }

    // 値をString型で返す
    public String toString() {
        return "現在のカウント：" + m_Count;
    }

    // C++でいうGetter
    public int getCount() {
        return m_Count;
    }
}