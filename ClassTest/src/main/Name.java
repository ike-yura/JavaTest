import java.util.Objects;

// 氏名（姓・名）を表すクラス
// 不変(immutable)オブジェクトとして設計されており、生成後に値を変更できない
public class Name {

    // ==============================
    // フィールド
    // ==============================

    // 名（first name）
    private final String m_firstName;

    // 姓（last name）
    private final String m_lastName;

    // ==============================
    // コンストラクタ
    // ==============================

    // 姓・名を指定してNameオブジェクトを生成
    public Name(String firstName, String lastName) {

        // 名がnullまたは空文字の場合は例外
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstNameが空かnullです");
        }

        // 姓がnullまたは空文字の場合は例外
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastNameが空かnullです");
        }

        // フィールドに代入（finalなので変更不可）
        m_firstName = firstName;
        m_lastName = lastName;
    }

    // ==============================
    // メソッド
    // ==============================

    // フルネームを「姓 名」の形式で返す
    public String getFullName() {
        return m_lastName + " " + m_firstName;
    }

    // オブジェクト内容を文字列で返す（デバッグ用）
    @Override
    public String toString() {
        return "Name(lastName=" + m_lastName
                + ", firstName=" + m_firstName + ")";
    }

    // ==============================
    // 等価性の定義
    // ==============================

    // 同じ姓・名の組み合わせなら等しいとみなす
    @Override
    public boolean equals(Object obj) {
        // 同一インスタンスならtrue
        if (this == obj) {
            return true;
        }

        // nullまたは型が異なる場合はfalse
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // キャストしてフィールド比較
        Name other = (Name) obj;
        return this.m_firstName.equals(other.m_firstName)
                && this.m_lastName.equals(other.m_lastName);
    }

    // equalsに対応するhashCode（MapやSet利用時に必要）
    @Override
    public int hashCode() {
        return Objects.hash(m_lastName, m_firstName);
    }
}
