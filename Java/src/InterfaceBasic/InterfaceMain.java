package InterfaceBasic;

import java.io.IOException;

public class InterfaceMain
{
	public static void main(String[] args) throws IOException
	{
		Logger logger = new ConsoleLogger();
		
		logger.log("test");
	}
}
