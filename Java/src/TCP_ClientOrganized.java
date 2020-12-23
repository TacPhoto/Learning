import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class TCP_ClientOrganized {
    static BufferedReader br;
    static BufferedWriter bw;
    static InputStream sis;
    static OutputStream sos;
    static InputStreamReader sisr;
    static OutputStreamWriter sosw;
    static Socket client;

    static String severName = "0.0.0.0";
    static int severPort = 10005;
    static InetAddress serverAddress;

    static FileWriter responseFileWriter;

    static {
        try {
            responseFileWriter = new FileWriter("TCP_Response.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TCP_ClientOrganized() throws IOException {
    }

    public static void sendRequest(String request) throws IOException {
        log("HTTP request sending");
        //String request = "GET /wireshark-labs/HTTP-wireshark-file1.html HTTP/1.0\r\n";
        log("Request", "Packet sent:    " + request);
        bw.write(request);
        bw.newLine();
        bw.newLine();
        bw.flush();
        log("HTTP request sent");
    }

    public static void getResponse() throws IOException {
        //log("HTTP response receiving");

        String response = br.readLine();

        while(response != null) {
            responseFileWriter.append(response + "\n");
            log("HTTP response", response);
            response = br.readLine();
        }

        responseFileWriter.flush();

        response = null;
    }

    public static void closeConnection() throws IOException {
        log("TCP connection closing - socket closing");
        sis.close();
        sos.close();
        client.close();
        log("TCP connection closed - socket closed");
        log("End");
    }

    public static void establishConnection() throws IOException {
        log("Start");
        log("Server name resolving (DNS)");
        serverAddress = InetAddress.getByName(severName);
        log("Server name resolved (DNS): " + serverAddress.toString());

        log("TCP connection creating - socket opening");
        client = new Socket(serverAddress, severPort);
        log("TCP connection created - socket opening");

        log("TCP streams collecting");
        sis = client.getInputStream();
        sos = client.getOutputStream();

        sisr = new InputStreamReader(sis);
        sosw = new OutputStreamWriter(sos);

        br = new BufferedReader(sisr);
        bw = new BufferedWriter(sosw);
    }

    public static void main(String[] args) throws IOException {
        establishConnection();
        sendRequest("%LISTLOCALFILES%\n");

        while(true) {
            getResponse();
        }
/*
        closeConnection();
        responseFileWriter.close();
 */
    }

    public static void log(String message) {
        System.out.println("[LOG]: " + message);
    }
    public static void log(String Type, String message) {
        System.out.println("[" + Type + "]:   " + message);
    }

}
