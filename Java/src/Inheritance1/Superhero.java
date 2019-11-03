package Inheritance1;


public class Superhero
{
	private int HP, strength, agility, stamina;
	
	public Superhero() {}
	public Superhero(int HP, int strength, int agility, int stamina)
	{
		this.HP = HP;
		this.strength = strength;
		this.agility = agility;
		this.stamina = stamina;
	}
	
	public int getHP()
	{
		return HP;
		
	}
	public void setHP(int HP)
	{
		this.HP = HP;
	}
	
	public int getStrength()
	{
		return strength;
	}
	public void setStrength(int strength)
	{
		this.strength = strength;
	}
	
	public int getAgility()
	{
		return agility;
	}
	public void setAgility(int agility)
	{
		this.agility = agility;
	}
	
	public int getStamina()
	{
		return stamina;
	}
	public void setStamina(int stamina)
	{
		this.stamina = stamina;
	}
	
}
