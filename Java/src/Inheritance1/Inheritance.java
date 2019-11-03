package Inheritance1;


public class Inheritance {
	public static void main(String[] args)
	{
		Superhero basicHero = new Superhero();
		
		basicHero.setHP(100);
		basicHero.setAgility(20);
		basicHero.setStrength(10);
		basicHero.setStamina(30);
		
		Batman batman = new Batman(150, 30, 30, 35, true);
		
		batman.setHP(batman.getHP() - 20);
		System.out.println(batman.getHP());
		
		batman.useBatmobil();
		
		System.out.println("Batman hascode = " + batman.hashCode());
	}
}