
public class BasicArrays
{
	public static void main(String[] args)
	{
		int [] arr = new int[10];
		
		for(int i = 0; i < arr.length; i++)
		{
		arr[i] = i;	
		}
		
		for (int record : arr)System.out.println(record);	
		
		int [] arr2 = { 22, 34, 56 };
		System.out.println("\n");
		for (int record : arr2 ) System.out.println(record);	

	}
}
