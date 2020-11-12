import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_Server {


    public static void main(String[] args) throws IOException {
        log("Start");
        int serverPort = 10000;
        log("Server socket opening");
        ServerSocket serverSocket = new ServerSocket(serverPort);
        log("Server socket opened");

        log("Server socket listening for incoming connections (accepting mode)");
        Socket client = serverSocket.accept();
        log("Server connection from client: " + client.getInetAddress().toString() + ":" + client.getPort());

        log("TCP streams collecting");
        InputStream sis = client.getInputStream();
        OutputStream sos = client.getOutputStream();
        InputStreamReader sisr = new InputStreamReader(sis);
        OutputStreamWriter sosw = new OutputStreamWriter(sos);
        BufferedReader br = new BufferedReader(sisr);
        BufferedWriter bw = new BufferedWriter(sosw);

        log("Waiting for request");
        String request = br.readLine();
        String requestEmptyLine = br.readLine();
        if(!requestEmptyLine.isEmpty()) {
            throw new RuntimeException("!requestEmptyLine.isEmpty()");
        }
        log("Request received: " + request);

        String response = request.toUpperCase();
        log("Response sending: " + response);
        bw.write(response);
        bw.newLine();
        bw.flush();
        log("Response sent: " + response);

        log("Client socket closing");
        client.close();
        log("Client socket closed");
        log("Server socket closing");
        serverSocket.close();
        log("Server socket closed");
        log("End");
    }

    public static void log(String message) {
        System.out.println("[S]: " + message);
    }
}