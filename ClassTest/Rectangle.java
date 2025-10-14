public class Rectangle {
    // メンバ変数
    private int m_Width = 0;// 幅
    private int m_Height = 0;// 高さ

    // コンストラクタ
    public Rectangle(int width, int height) {
        if (width >= 1) {
            m_Width = width;
        } else {
            m_Width = 1;
        }

        if (height >= 1) {
            m_Height = height;
        } else {
            m_Height = 1;
        }
    }

    public static Rectangle fromSquare(int size) {
        int s = (size < 1) ? 1 : size;   // 1 に丸める
        return new Rectangle(s, s);      // 新しい長方形を作って返す
    }


    //面積
    public int getArea(){
        return m_Width*m_Height;
    }

    // 周長
    public int getPermeter(){
        return 2*(m_Width+m_Height);
    }

    @Override
    public String toString(){
        return "RectAngle(width=" + m_Width + ", height" + m_Height + 
        ", area" + getArea() + ", perimeter" + getPermeter() +")";
    }

    //getter setter
    public int getHeight() { return m_Height;}
    public int getWidth() { return  m_Width;}

    public boolean setWidth(int value){
          if(value >= 1){
            m_Width = value;
            return true;
        }else{
            return false;
        }
    }

    public boolean setHeight(int value){
        if(value >= 1){
            m_Height = value;
            return true;
        }else{
            return false;
        }
    }
}