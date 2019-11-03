
public class Constructors
{ 
	public static void main(String[] args)
	{
		Point point = new Point(10, 15);
		
		System.out.println("Point1\nx: " + point.x);
		System.out.println("y: " + point.y);

		System.out.println("-----------------------------------");
		
		Point point2 = new Point(point);
		point2.x = 22;
		point2.y = 33;
		
		System.out.println("Point1\nx: " + point.x);
		System.out.println("y: " + point.y);
		
		System.out.println("Point2\nx: " + point2.x);
		System.out.println("y: " + point2.y);
	}
}
