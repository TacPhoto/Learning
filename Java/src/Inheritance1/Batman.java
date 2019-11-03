package Inheritance1;


public class Batman extends Superhero
{
	private boolean batmobil;
	
	public Batman(int HP, int strength, int agility, int stamina, boolean batmobil)
	{
		super(HP, strength, agility, stamina);
		this.batmobil = batmobil;
	}

	public void useBatmobil()
	{
		if(batmobil)
		{
			System.out.println("Batmobil used");
		}else{
			System.out.println("You left the batmobil in the garage");
		}
	}
}
