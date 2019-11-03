
public class PersonObject
{
	public static class Person
	{
		String name;
		int age;
		boolean isAlive;
		
		void SayHello()
		{
			System.out.println("Hey! My name is " + name);
		}
	}
	
	
	public static void main(String[] args)
	{
		Person adam = new Person();
		adam.name = "Adam";
		adam.age = 20;
		adam.isAlive = true;
		
		adam.SayHello();
	}
}
