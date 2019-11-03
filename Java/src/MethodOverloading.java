
public class MethodOverloading
{
	public static void main(String[] args)
	{
			displayName();
			displayName("Hans");
	}
	
	
	static void displayName()
	{
		System.out.println("Andrei");
	}
	
	static void displayName(String name)
	{
		System.out.println(name);
	}
}
