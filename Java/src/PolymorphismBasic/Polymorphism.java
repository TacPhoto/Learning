package PolymorphismBasic;

public class Polymorphism
{
	public static void main(String[] args)
	{
		Man [] guysArray = new Man[4];
		
		guysArray[0] = new Man(20, 4);
		guysArray[1] = new Man(20,9);
		guysArray[2] = new CoolGuy(23, 3);
		guysArray[3] = new Man(25, 11);

		for (Man i : guysArray)
		{
			i.sayHello();
		}
		
		System.out.println("-----------------------");
		
		greetMan(guysArray[1]);
	}
	
	
	public static void greetMan(Man i)
	{
		i.sayHello();
	}
}
