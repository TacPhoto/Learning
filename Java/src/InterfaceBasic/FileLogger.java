package InterfaceBasic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger
{
	private static FileWriter writer;

	public void log(String text) throws IOException
	{
		File file = new File("javeFile1.txt");
		writer = new FileWriter(file);
		
		writer.write(text + "\n");
	}
}
