package FileTransfer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

import static FileTransfer.Logger.log;

public class TCP_ServerThread implements Runnable{

    public static ServerSocket serverSocket;
    static Socket clientSocket;
    public static int port;
    static InputStream sis;
    static InputStreamReader sisr;
    static BufferedReader br;
    static FileUtils fileUtils;
    static RequestParser requestParser;

     TCP_ServerThread(int port, FileUtils fileUtils) throws IOException {
         TCP_ServerThread.port = port;

         ServerSocket serverSocket = new ServerSocket(port);
         clientSocket = serverSocket.accept();

         sis = clientSocket.getInputStream();
         sisr = new InputStreamReader(sis);
         br = new BufferedReader(sisr);

         TCP_ServerThread.fileUtils = fileUtils;
         requestParser = new RequestParser(fileUtils, clientSocket);
     }

    TCP_ServerThread(Socket socket, FileUtils fileUtils) throws IOException {
         port = socket.getPort();
       clientSocket = socket;

        sis = clientSocket.getInputStream();
        sisr = new InputStreamReader(sis);
        br = new BufferedReader(sisr);

        TCP_ServerThread.fileUtils = fileUtils;
        requestParser = new RequestParser(fileUtils, clientSocket);
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
         StringBuilder stringBuilder = new StringBuilder();
         String s = "";

         while(!s.equals("%REQUESTEND%"))
         {
            try {
                s = br.readLine();
                if(s != null) {
                    stringBuilder.append(s + "\n");
                }
            }catch (IOException e){
                return null;
            }
         }
         return stringBuilder.toString();
        }

        public void run() {
         String oldRequest = "";
            try {
                String request = receiveRequest();

                if(request != null) {

                    while (!request.equals("%CLOSECONNECTION%")) {
                        log("Request: " + request);

                        if(request.equals(oldRequest)){
                            sendMessage("Skipped parsing message due to two identical requests in a row during the same connection." +
                                    "Next request will be parsed ignoring this check");
                            oldRequest = "";
                            continue;
                        }

                        sendMessage(requestParser.returnMessage(request));

                        oldRequest = request;
                    }
                    request = null;
                }

                log("Connection closed, client:" + clientSocket.toString());
                clientSocket.close();
            } catch (IOException | NoSuchAlgorithmException e) {
                System.out.println(e);
            }
        }
}
