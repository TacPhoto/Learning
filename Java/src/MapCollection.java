import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapCollection
{
	public static void main(String[] args)
	{
		Map<Integer, String> map = new HashMap<>();
		
		map.put(1, "one");
		map.put(2, "two");
		
		for(Entry<Integer, String> m : map.entrySet())
		{
			int key = m.getKey();
			String value = m.getValue();
			
			System.out.println("Key:" + key + " " + value);
		}
		
		System.out.println("---------------");
		System.out.println(map.containsKey(2));
		System.out.println(map.get(1));
		System.out.println(map.values());
		System.out.println(map.keySet());
	}
}
