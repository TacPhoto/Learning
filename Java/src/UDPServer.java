    import java.io.*;
    import java.net.*;
    class UDPServer {
        public static void main(String args[]) throws Exception
        {
            DatagramSocket serverSocket = new DatagramSocket(5521);
            byte[] receiveData = new byte[4096];
            byte[] sendData = new byte[1024];
            sendData = "\n".getBytes();

            while(true)
            {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());

                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                if(!sentence.isEmpty()){
                    System.out.println(sentence);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    serverSocket.send(sendPacket);

                }
            }
        }
    }