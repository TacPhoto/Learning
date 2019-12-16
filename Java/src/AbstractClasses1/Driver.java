package AbstractClasses1;

public class Driver
{
    public static void main(String[] arg)
    {
        EquilateralTriangle equilateralTriangle1 = new EquilateralTriangle(3.4f);
        equilateralTriangle1.showParams();

        Square square1 = new Square(4.f);
        System.out.println("Square area: " + square1.getArea());

        Shape shape1 = new Square(5.f);
        shape1.showParams();
    }
}
