import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BasicFileIO
{
	public static void main(String[] args) throws FileNotFoundException
	{
		File someFile = new File ("javaFile1.txt");
		Scanner scanner = new Scanner(someFile);

		if(someFile.exists()) System.out.println("The file exists!");
		System.out.println("File path: " + someFile.getAbsolutePath() + "\n");
		
		while(scanner.hasNext()) System.out.println(scanner.nextLine());
		System.out.println();
		////
		File scndFile = new File ("javaFile2.txt");
		try {
			scndFile.createNewFile();
			System.out.println("New file created!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PrintWriter writer = new PrintWriter(scndFile);
		writer.println("First line");
		writer.close();
	}
}
