package FileTransfer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import static FileTransfer.Logger.log;
import static FileTransfer.Logger.prompt;

public class FileTransfer {

    static ArrayList<HostData> hostsList = new ArrayList<HostData>();
    static String commandType;
    static String rootPath;
    static String filename;
    static FileUtils fileUtils;
    static int serverPort;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        //  server port, home_directory, listFiles, hosts...
        //  server port, home_directory, pull, name, hosts..
        //  server port, home_directory, push, name, hosts..
        //  man / help

        // TODO:  MERGE DOWNLOADED PARTS

        // TODO: RUN COMMAND TO CONTINUE DOWNLOADING FILE
        //  - it is based on file with final MD5, file name and number of all parts
        //  - simply asks hosts for missing parts and then merges them


        serverPort = 10005; // default port

        rootPath = "";
        parseLaunchArguments(args);

        System.out.println("Local server port: " + serverPort);

        if(rootPath.equals("")) { //debug folder path if no arguments passed
            rootPath = "Z:\\GitHubLearning\\Learning\\Java\\bin\\FileTransfer\\Files1";
        }
        fileUtils = new FileUtils(rootPath);
        fileUtils.updateLocalFileList();

        Thread serverDriver = new Thread(new ServerDriver(serverPort, fileUtils));
        serverDriver.start();

        if(args.length > 2){
            //other commands than just a listener

            if (commandType.equals("pull")) {
                // single host pull request
                if (hostsList.size() == 1) {
                    sendInitMessage(hostsList.get(0).port,
                            hostsList.get(0).IP,
                            "%PULL% " + filename +
                                    "\r\n%REQUESTEND%\r\n");
                }
                // multihost pull request (parts)
                if (hostsList.size() > 1) {
                    ArrayList<HostData> tempHostsList = new ArrayList<HostData>();

                    for (int i = 0; i < hostsList.size(); i++) {
                        // no need for threads yet, this response goes in the main thread
                        if (getResponseThroughBasicClient(hostsList.get(i).port,
                                hostsList.get(i).IP,
                                "%DOYOUHAVE% " + filename + "\r\n%REQUESTEND%\r\n"
                        ).contains("%IHAVE%")) {
                            tempHostsList.add(hostsList.get(i));
                        }
                    }
                    // file parts saving etc will be handled inside server thread initialized in sendInitMessage

                    for (int j = 0; j < tempHostsList.size(); j++) {
                        // this is multithreaded
                        sendInitMessage(tempHostsList.get(j).port,
                                tempHostsList.get(j).IP,
                                "%PULLPART% " + " " +
                                        (j + 1) + " "
                                        + tempHostsList.size() +
                                        " " + filename + "\r\n\"%REQUESTEND%\r\n");
                    }
/*
                    sendInitMessage(tempHostsList.get(0).port,
                            tempHostsList.get(0).IP,
                            "%PULLPART% " + " " +
                                    (0 + 1) + " "
                                    + tempHostsList.size() +
                                    " " + filename + "\r\n\"%REQUESTEND%\r\n");

                    sendInitMessage(tempHostsList.get(1).port,
                            tempHostsList.get(1).IP,
                            "%PULLPART% " + " " +
                                    (1 + 1) + " "
                                    + tempHostsList.size() +
                                    " " + filename + "\r\n\"%REQUESTEND%\r\n");


                     */

                }
            } else {

                for (HostData host : hostsList) {
                    switch (commandType) {
                        case "push":
                            FileData fileData = fileUtils.getFile(filename);
                            if (fileData != null) {
                                String fileContent = FileUtils.fileToBase64String(fileData);
                                String message = new String("%FILEBEGINNING%\r\n" +
                                        fileData.file.getName() + "\r\n" +
                                        fileData.checkSum + "\r\n" +
                                        fileContent + "\r\n%FILEEND%\r\n" +
                                        "%REQUESTEND%\r\n");
                                sendInitMessage(host.port, host.IP, message);
                            }
                            break;

                        case "listFiles":
                            String list = (getResponseThroughBasicClient(host.port, host.IP, "%LISTLOCALFILES% +\n" +
                                    "%REQUESTEND%\r\n"));
                            prompt(list);
                            break;
                    }
                }
            }
        }



        Scanner scanner = new Scanner(System.in); // don't laugh, I keep console open this way Justin Case
        scanner.nextLine();

    }

    private static void sendInitMessage(int port, String ip, String message) throws IOException {
        // avoiding passing static variable to a thread
        FileUtils tempFileUtils = new FileUtils(fileUtils.rootPath.toAbsolutePath().toString());
        new Thread(new BasicRequestThread(port, ip, tempFileUtils, message)).start();
    }

    private static String getResponseThroughBasicClient(int port, String ip, String message) throws IOException {
        InetAddress serverAddress = InetAddress.getByName(ip);
        Socket client = new Socket(serverAddress, port);

        InputStream sis = client.getInputStream();
        OutputStream sos = client.getOutputStream();

        InputStreamReader sisr= new InputStreamReader(sis);
        OutputStreamWriter sosw= new OutputStreamWriter(sos);

        BufferedReader br = new BufferedReader(sisr);
        BufferedWriter bw = new BufferedWriter(sosw);

        bw.write(message);
        bw.flush();

        StringBuilder s = new StringBuilder(br.readLine());
        String line = "";
        while(line != null) {
            //log("getResponseThroughBasicClient: " + s);
            line = br.readLine();

            if(line == null)
                break;

            if(line.contains("%CLOSECONNECTION%"))
                break;

            // this condition is not be needed but may prevent doubled messages
            if(!s.toString().contains(line)) {
                s.append(line + "\n");
            }
        }

        String response = s.toString();

        sos.close();
        sis.close();
        client.close();
        return response;
    }

    private static void parseLaunchArguments(String[] args) {
        if(args.length == 0){
            System.out.println("No launch arguments passed");
            return;
        }

        if(args[0].equals("man") || args[0].equals("help")){

            showHelp();
            return;
        }

        serverPort = Integer.parseInt(args[0]);

        //  server port, home_directory, listFiles, hosts...
        //  server port, home_directory, pull, name, hosts..
        //  server port, home_directory, push, name, hosts..

        rootPath = args[1].trim();
        if(!Files.exists(Paths.get(rootPath))){
            rootPath = "";
            System.out.println("Home directory not found");
            return;
        }

        if(args.length < 3){
            return;
        }

        commandType = args[2].trim();

        int i = 3;
        if (commandType.equals("pull") || commandType.equals("push")) {
            filename = args[3].trim();
            i++;
        }

        for(; i < args.length; i++){
            String[] hostInfo = args[i].trim().split(":");
            String IP_parsed = hostInfo[0];
            int port_parsed = Integer.parseInt(hostInfo[1]);

            hostsList.add(new HostData(port_parsed, IP_parsed));
        }


    }

    private static void showHelp() {
    }

}


