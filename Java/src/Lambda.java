
public class Lambda
{
	public static void main(String[] args)
	{	
		LambdaInterface sum = (a, b) -> a + b;
		
		LambdaInterface c = new LambdaInterface()
		{
			@Override
			public int sum(int a, int b)
			{
				return a + b;
			}
		};
	
		System.out.println(sum.sum(2, 5));
		System.out.println(c.sum(2, 5));
	}
}
