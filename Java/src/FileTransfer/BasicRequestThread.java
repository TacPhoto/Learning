package FileTransfer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import static FileTransfer.Logger.log;
import static FileTransfer.Logger.prompt;


public class BasicRequestThread implements Runnable{
    public static InetAddress serverAddress;
    static BufferedReader br;
    static BufferedWriter bw;
    static InputStream sis;
    static OutputStream sos;
    static InputStreamReader sisr;
    static OutputStreamWriter sosw;
    public static Socket socket;
    public static String message;
    BasicRequestThread(int port, String ip, String message) throws IOException {
        serverAddress = InetAddress.getByName(ip);
        socket = new Socket(serverAddress, port);
        this.message = message;
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

/*
            sos.close();
            sis.close();
            socket.close();*/
        } catch (IOException e) {
            e.printStackTrace();
    }
    }
}
