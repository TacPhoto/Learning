package PolymorphismBasic;

public class Man
{
	protected int age, cool;
	
	public Man(){}
	public Man(int age, int cool)
	{
		this.age = age;
		this.cool = cool;
	}
	
	void sayHello()
	{
		if(cool >= 10)
			System.out.println("Hello ladies!");
		else
			System.out.println("Good evening");
	}
	
}
