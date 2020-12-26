package FileTransfer;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static FileTransfer.Logger.log;

public class ServerDriver implements  Runnable{
    public static int port;
    static FileUtils fileUtils;

    ServerSocket serverSocket = null;
    Socket socket = null;
    public ServerDriver(int port, FileUtils fileUtils) throws IOException {
        ServerDriver.port = port;
        ServerDriver.fileUtils = fileUtils;

        try {
            serverSocket = new ServerSocket(port);
        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
        while (true) {
            try {
                socket = serverSocket.accept();
                log("Launching tew server thread");
                new Thread(new TCP_ServerThread(socket, fileUtils)).start();

            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }

        }
    }
}
