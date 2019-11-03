package PolymorphismBasic;

public class CoolGuy extends Man
{
	public CoolGuy(int age, int cool)
	{
		super(age, cool);
		this.cool = cool + 10;
	}
	
	public void whatsUp()
	{
		System.out.println("Whaaaaats up maaaan?!");
	}
}
