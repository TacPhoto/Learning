package KlientSerwerThread;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
class Klient {
    public static void main(String[] args) throws IOException {
        log("Start");
        //String severName = "gaia.cs.umass.edu";
        //int severPort = 80;
        String severName = "0.0.0.0";
        int severPort = 8080;
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
        String request = "\n";
        bw.write(request);
        bw.newLine();
        request = "\n";
        bw.write(request);
        bw.newLine();
        bw.flush();
        log("HTTP request sent");
        log("HTTP response receiving");
        String response = br.readLine();
        log("HTTP response received: " + response);

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