package InterfaceBasic;

public class ConsoleLogger implements Logger
{
	public void log(String text)
	{
		System.out.println(text);
	}	
}
