import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SetCollection
{
	public static void main(String[] args)
	{
		Set<String> setStr = new HashSet<>();
		
		setStr.add("one");
		setStr.add("two");
		setStr.add("three");
		setStr.add("four");
		setStr.add("three"); //will only change the order
		
		printSetStr(setStr);
		
		System.out.println("-----------------");
		
		Set<Integer> newSet = new HashSet<>();
		Random r = new Random();
		
		while(newSet.size() < 6 )
		{
			int x = r.nextInt(37);
			System.out.println("Num: " + x);
			newSet.add(x);
		}
		
		printSetInt(newSet);
	}

	
	public static void printSetStr(Set<String> set)
	{
		for(String s : set)
		{
			System.out.println(s);
		}
	}
	
	
	public static void printSetInt(Set<Integer> set)
	{
		for(Integer s : set)
		{
			System.out.println(s);
		}
	}

}
