import java.math.BigInteger;
import java.math.BigDecimal;

public class MathOperations
{
	public static double AreaCircle(double radius)
	{
		return Math.pow(radius, 2) * Math.PI;
	}
	
	
	public static void main(String[] args)
	{
	System.out.println(AreaCircle(3.8));
	System.out.println(AreaCircle(10.0));

	BigInteger num1 = new BigInteger("9999999999999999999999999999999999999999999999999999");
	BigInteger num2 = new BigInteger("123456789098765432223456789098765434565436789322");

	System.out.println(num1.add(num2));
	BigInteger num3 = num1.add(num2);
	System.out.println(num3);
	
	System.out.println("------------------------------------------------");

	BigDecimal dec1 = new BigDecimal("5000000000000000000000000.7777777777777777777777777");
	BigDecimal dec2 = new BigDecimal("3333333333333300000000000.3333333333356789333333333");
	System.out.println(dec1.add(dec2));
	
	System.out.println("------------------------------------------------");

	double one = 7.4;
	double two = 3.0;
	System.out.println(one / two);
	
	int one2 = 7;
	int two2 = 3;
	System.out.println(one2 % two2);
	}
}
