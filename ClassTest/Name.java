import java.util.Objects;

public class Name {
    // メンバ変数
    private final String m_firstName;
    private final String m_lastName;

    // コンストラクタ
    public Name(String firstName, String lastName) {
        // firstNameの処理
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstNameが空かnullです");// 例外投げる。
        }

        // lastNameの処理
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastNameが空かnullです");// 例外投げる。
        }

        m_firstName = firstName;
        m_lastName = lastName;
    }

    //
    public String getFullName() {
        return m_lastName + " " + m_firstName;
    }

    @Override
    public String toString() {
        return "Name(lastName=" + m_lastName
                + ",firstName=" + m_firstName + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;// 同一参照なのでtrue
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;// objがnullまたは方が違うのでfalse
        }

        // キャスト
        Name other = (Name) obj;
        // フィールド比較
        return this.m_firstName.equals(other.m_firstName)
                && this.m_lastName.equals(other.m_lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_lastName, m_firstName);
    }
}
