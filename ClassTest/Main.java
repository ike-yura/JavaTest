public class Main {
    public static void main(String[] args) {
        Rectangle r = Rectangle.fromSquare(2);
        Rectangle r2 = new Rectangle(30, 40);

        r.setHeight(400);

        System.out.println(r);
        System.out.println(r2);
    }
}