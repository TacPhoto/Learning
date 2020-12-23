package FileTransfer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static FileTransfer.Logger.log;

public class TCP_Server implements Runnable{

    public static ServerSocket serverSocket;
    static Socket clientSocket;
    public static int port;
    static InputStream sis;
    static InputStreamReader sisr;
    static BufferedReader br;
    static FileUtils fileUtils;
    static RequestParser requestParser;

     TCP_Server(int port, FileUtils fileUtils) throws IOException {
         this.port = port;

         ServerSocket serverSocket = new ServerSocket(this.port);
         Socket socket = serverSocket.accept();
         this.clientSocket = socket;

         sis = this.clientSocket.getInputStream();
         sisr = new InputStreamReader(sis);
         br = new BufferedReader(sisr);

         this.fileUtils = fileUtils;
         requestParser = new RequestParser(fileUtils);


     }

        public static void sendMessage() throws IOException{
            PrintStream outputStream = new PrintStream(clientSocket.getOutputStream());
            outputStream.println("server test response\n");
            outputStream.close();
        }

        public static void sendMessage(String message) throws IOException{
            PrintStream outputStream = new PrintStream(clientSocket.getOutputStream());
            outputStream.print(message);
            outputStream.close();
        }

        public static String receiveRequest(){
            try {
                return br.readLine();
            }catch (IOException e){
                return null;
            }
        }

        public void run() {
            try {
                String request = receiveRequest();
                log("Request: " + request);

                sendMessage(requestParser.returnMessage(request));

                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
}
