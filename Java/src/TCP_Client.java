import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;

public class TCP_Client {


    public static void main(String[] args) throws IOException {
        log("Start");
        String severName = "gaia.cs.umass.edu";
        int severPort = 80;
        //String severName = "localhost";
        //int severPort = 10000;
        log("Server name resolving (DNS)");
        InetAddress serverAddress = InetAddress.getByName(severName);
        log("Server name resolved (DNS): " + serverAddress.toString());

        log("TCP connection creating - socket opening");
        Socket client = new Socket(serverAddress, severPort);
        log("TCP connection created - socket opening");

        log("TCP streams collecting");
        InputStream sis = client.getInputStream();
        OutputStream sos = client.getOutputStream();

        InputStreamReader sisr = new InputStreamReader(sis);
        OutputStreamWriter sosw = new OutputStreamWriter(sos);

        BufferedReader br = new BufferedReader(sisr);
        BufferedWriter bw = new BufferedWriter(sosw);

        log("HTTP request sending");
        String request = "GET /wireshark-labs/HTTP-wireshark-file1.html HTTP/1.0\r\n";
        log("Packet sent:    " + request);
        bw.write(request);
        bw.newLine();
        bw.newLine();
        bw.flush();
        log("HTTP request sent");

        log("HTTP response receiving");
        //String response = br.readLine();

        String response = br.readLine();
        while(response != null) {
            log("HTTP response received: " + response);
            response = br.readLine();
        }

        log("TCP connection closing - socket closing");
        sis.close();
        sos.close();
        client.close();
        log("TCP connection closed - socket closed");
        log("End");
    }

    public static void log(String message) {
        System.out.println("[C]: " + message);
    }

}
