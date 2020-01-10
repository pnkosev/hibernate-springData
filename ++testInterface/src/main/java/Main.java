import entities.*;

public class Main {
    public static void main(String[] args) {
//        Rectangle rectangle = new Rectangle(5, 10);
//        System.out.println(rectangle.calculateArea());
//
//        Square square = new Square(5);
//        System.out.println(square.calculateArea());
//
//        Triangle triangle = new Triangle(5, 10);
//        System.out.println(triangle.calculateArea());
//
//        Ellipse ellipse = new Ellipse(5, 10);
//        System.out.println(ellipse.calculateArea());
//
//        Circle circle = new Circle(5);
//        System.out.println(circle.calculateArea());

        boolean isWhereClause = false;

        StringBuilder sb = new StringBuilder();

        if (someBooleanFunc()) {
            isWhereClause = true;
        }

        StringBuilder stringBuilder = isWhereClause ? sb.append("yo") : sb.append("oy");

        System.out.println(sb.toString());
    }

    private static boolean someBooleanFunc() {
        return true;
    }
}
