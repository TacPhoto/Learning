package Exercise1;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Rewrite
{
    public static void main(String args[]) throws IOException {
     String inputPath = "Z:\\serverLog.txt";
     String outputPath = "Z:\\goLrevres.txt";
     String content = "";

     try
     {
         content = new String ( Files.readAllBytes( Paths.get(inputPath) ) );
     }
     catch (IOException e){e.printStackTrace();}

     System.out.println(content);

     int len = content.length();
     char[] tempCharArray = new char[len];
     char[] charArray = new char[len];

     for (int i = 0; i < len; i++) {
         tempCharArray[i] = content.charAt(i);
     }
     for (int j = 0; j < len; j++) {
         charArray[j] = tempCharArray[len - 1 - j];
     }

     String reversed = new String(charArray);
     System.out.println(reversed);

     FileOutputStream outputStream = new FileOutputStream(outputPath);
     DataOutputStream dataOutStream = new DataOutputStream(new BufferedOutputStream(outputStream));
     dataOutStream.writeUTF(reversed);

     dataOutStream.close();
    }
}
