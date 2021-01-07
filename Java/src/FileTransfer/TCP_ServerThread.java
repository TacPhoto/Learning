package FileTransfer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

import static FileTransfer.Logger.log;
import static FileTransfer.Logger.prompt;

public class TCP_ServerThread implements Runnable{

    public  ServerSocket serverSocket;
    Socket clientSocket;
    public static int port;
    InputStream sis;
    InputStreamReader sisr;
    BufferedReader br;
    FileUtils fileUtils;
    RequestParser requestParser;
    String initMessage;

     TCP_ServerThread(int port, FileUtils fileUtils, String initMessage) throws IOException {
         TCP_ServerThread.port = port;

         ServerSocket serverSocket = new ServerSocket(port);
         clientSocket = serverSocket.accept();

         sis = clientSocket.getInputStream();
         sisr = new InputStreamReader(sis);
         br = new BufferedReader(sisr);

         this.fileUtils = fileUtils;
         requestParser = new RequestParser(fileUtils, clientSocket);

         this.initMessage = initMessage;
     }

    TCP_ServerThread(Socket socket, FileUtils fileUtils, String initMessage) throws IOException {
        port = socket.getPort();
        clientSocket = socket;

        sis = clientSocket.getInputStream();
        sisr = new InputStreamReader(sis);
        br = new BufferedReader(sisr);

        this.fileUtils = fileUtils;
        requestParser = new RequestParser(fileUtils, clientSocket);

        this.initMessage = initMessage;

    }

    TCP_ServerThread(Socket socket, FileUtils fileUtils) throws IOException {
         port = socket.getPort();
       clientSocket = socket;

        sis = clientSocket.getInputStream();
        sisr = new InputStreamReader(sis);
        br = new BufferedReader(sisr);

        this.fileUtils = fileUtils;
        requestParser = new RequestParser(fileUtils, clientSocket);
    }

        public void sendMessage() throws IOException{
            PrintStream outputStream = new PrintStream(clientSocket.getOutputStream());
            outputStream.println("server test response\n");
            outputStream.close();
        }

        public void sendMessage(String message) throws IOException{
            PrintStream outputStream = new PrintStream(clientSocket.getOutputStream());
            outputStream.print(message);
            outputStream.close();
        }

        public String receiveRequest(){
         StringBuilder stringBuilder = new StringBuilder();
         String s = "";

         while(!s.contains("%REQUESTEND%"))
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
         if(initMessage != null){
             try {
                 sendMessage(initMessage);
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }

         String oldRequest = "";
            try {
                String request = receiveRequest();

                if(request != null) {
                    //log("request: " + request);
                    while (!request.equals("%CLOSECONNECTION%")) {
                        prompt("Request: " + request);

                        if(request.equals(oldRequest)){
                            sendMessage("Skipped parsing message due to two identical requests in a row during the same connection." +
                                    "Next request will be parsed ignoring this check");
                            oldRequest = "";
                            continue;
                        }

                        sendMessage(requestParser. returnMessage(request));

                        oldRequest = request;
                    }
                    request = null;
                }
                sendMessage("%CLOSECONNECTION%");
                log("Connection closed, client:" + clientSocket.toString());
                clientSocket.close();
            } catch (IOException | NoSuchAlgorithmException e) {
                System.out.println(e);
            }
        }
}
