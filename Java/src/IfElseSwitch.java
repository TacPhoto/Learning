
public class IfElseSwitch
{
	public static void main(String[] args)
	{
		if(0 == 0) System.out.println("Zero is equal to zero! Wonderful!");
		
		if(0 == 1)
		{
		System.out.println("Something, something...");
		}
		else
		{
		System.out.println("Something else");			
		}
		
		int var = 2;
		switch(var)
		{
		case 1:
			System.out.println("1");			
			break;
		case 2:
			System.out.println("2");			
			break;
		case 3:
			System.out.println("3");			
			break;
		default:
			System.out.println("Default");
		}
	}
}
