import java.util.ArrayList;
import java.util.List;

public class ListCollection 
{
	public static void main(String[] args)
	{
		List<String> listStr = new ArrayList<>();
		
		for(String i : listStr)
		{
			System.out.println(i);
		}
		
		listStr.add("first");
		listStr.add("scnd");
		listStr.add("thrd");
		
		printList(listStr);
		
		listStr.add(1, "zero");
		
		printList(listStr);
		
		listStr.remove(3);
		printList(listStr);
		
		System.out.println(listStr.contains("zero"));
		System.out.println(listStr.indexOf("scnd"));
		printLine();
		
		listStr.clear();
		printList(listStr);
	}
	
	
	public static void printList(List<String> list)
	{
		for(String i : list)
		{
			System.out.println(i);
		}
		
		printLine();
	}
	
	
	public static void printLine()
	{
		System.out.println("-----------------------");
	}
}
