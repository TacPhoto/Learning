package FileTransfer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

import static FileTransfer.Logger.log;
import static FileTransfer.Logger.prompt;


public class BasicRequestThread implements Runnable{
    public static InetAddress serverAddress;
    BufferedReader br;
    BufferedWriter bw;
    InputStream sis;
    OutputStream sos;
    InputStreamReader sisr;
    OutputStreamWriter sosw;
    Socket socket;
    String message;
    RequestParser requestParser;
    FileUtils fileUtils;

    BasicRequestThread(int port, String ip, FileUtils fileUtils,String message) throws IOException {
        serverAddress = InetAddress.getByName(ip);
        socket = new Socket(serverAddress, port);
        this.message = message;
        this.fileUtils = fileUtils;
        requestParser = new RequestParser(this.fileUtils, socket);

        log("Thread Sending: [" + message + "] to Socket: " + socket);

    }

    @Override
    public void run() {
        try {
            sis = socket.getInputStream();
            sos= socket.getOutputStream();

            sisr = new InputStreamReader(sis);
            sosw = new OutputStreamWriter(sos);

            br = new BufferedReader(sisr);
            bw = new BufferedWriter(sosw);

            bw.write(message);
            bw.newLine();
            bw.newLine();
            bw.flush();

            String s = "";
            StringBuilder stringBuilder = new StringBuilder();
            s = br.readLine();

            while(s != null) {
                stringBuilder.append(s + "\n");
                s = br.readLine();
            }

            String response = stringBuilder.toString();
            if(!response.equals(""))
                prompt("BasicRequestThread received: " + response);

            requestParser.returnMessage(response); // saves received files etc

/*
            sos.close();
            sis.close();
            socket.close();*/
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
    }
    }
}
