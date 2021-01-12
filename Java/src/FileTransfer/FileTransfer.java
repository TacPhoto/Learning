package FileTransfer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static FileTransfer.Logger.log;
import static FileTransfer.Logger.prompt;

public class FileTransfer {

    static ArrayList<HostData> hostsList = new ArrayList<HostData>();
    static String commandType;
    static String rootPath;
    static String filename;
    static FileUtils fileUtils;
    static int serverPort;
    static int continuePartsOverallAmount;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        //  server port, home_directory, listFiles, hosts...
        //  server port, home_directory, pull, name, hosts..
        //  server port, home_directory, push, name, hosts..
        //  server port, home_directory, continue, name, amount_parts, hosts...
        //  man / help

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

                    ArrayList<Thread> serverThreadList = new ArrayList<Thread>();

                    // file parts saving etc will be handled inside server thread initialized in sendInitMessage
                    for (int j = 0; j < tempHostsList.size(); j++) {
                        // this is multithreaded
                        serverThreadList.add(sendInitMessage(tempHostsList.get(j).port,
                                tempHostsList.get(j).IP,
                                "%PULLPART% " + " " +
                                        (j + 1) + " "
                                        + tempHostsList.size() +
                                        " " + filename + "\r\n\"%REQUESTEND%\r\n")
                        );
                    }

                    // wait till all downloads are finished
                    for(int i = 0; i < serverThreadList.size(); i++){
                        while(serverThreadList.get(i).isAlive()) {
                            // wait (yes, I know it's not a legit way)
                        }
                    }

                    prompt("Part downloads threads finished, file merger called");

                    mergeFiles(filename);

                }
            } else if (commandType.equals("continue")){
                // find out which files are missing
                File[] allFiles = new File(fileUtils.downloadedFilesPath.toString()).listFiles();
                ArrayList<File> files = new ArrayList<>();
                ArrayList<Integer> partsWeGot = new ArrayList<>();

                log("Filtering files to get fileparts which were downloaded previously");

                for(File file : allFiles){
                    String fullName = file.getName(); //  name, format, 'part', partnum [no number on purpose]
                    String nameParts[] = fullName.split("\\.");
                    log("part 0: " + nameParts[0]);
                    //skip files not made of parts (they have name, format, part, partnum) and that are duplicates
                    if(nameParts.length != 4){
                        continue;
                    }

                    if( (nameParts[0] + "." + nameParts[1]).equals(filename) ){
                        files.add(file);
                        partsWeGot.add(Integer.parseInt(nameParts[nameParts.length - 1]));
                    }
                }

                // condition checks if we need to download anything
                if(partsWeGot.size() < continuePartsOverallAmount) {

                    ArrayList<HostData> tempHostsList = new ArrayList<HostData>();
                    // find out which servers got files
                    for (int i = 0; i < hostsList.size(); i++) {
                        // no need for threads yet, this response goes in the main thread
                        if (getResponseThroughBasicClient(hostsList.get(i).port,
                                hostsList.get(i).IP,
                                "%DOYOUHAVE% " + filename + "\r\n%REQUESTEND%\r\n"
                        ).contains("%IHAVE%")) {
                            tempHostsList.add(hostsList.get(i));
                        }
                    }

                    // ask servers for files
                    ArrayList<Thread> serverThreadList = new ArrayList<Thread>();
                    for(int i = 1; i <= continuePartsOverallAmount; i++) {
                        boolean skip = false;
                        for(int k = 0; k < partsWeGot.size(); k++) {
                            if (i == partsWeGot.get(k)) {
                                skip = true;
                            }
                        }

                        if(skip){
                            continue;
                        }

                        int hostnum = i % tempHostsList.size();

                        serverThreadList.add(sendInitMessage(tempHostsList.get(hostnum).port,
                                tempHostsList.get(hostnum).IP,
                                "%PULLPART% " + " " +
                                        (i) + " "
                                        + continuePartsOverallAmount +
                                        " " + filename + "\r\n\"%REQUESTEND%\r\n")
                        );
                    }

                    // wait till all downloads are finished
                    for(int i = 0; i < serverThreadList.size(); i++){
                        while(serverThreadList.get(i).isAlive()) {
                            // wait (yes, I know it's not a legit way)
                        }
                    }
                }
                // merge files
                prompt("Redownload finished, file merger called");

                mergeFiles(filename);
            }else{
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

    private static void mergeFiles(String filename) throws IOException {
        // works only for files which don't have duplicates (they have number prefix)
            File[] allFiles = new File(fileUtils.downloadedFilesPath.toString()).listFiles();
            ArrayList<File> files = new ArrayList<>();

            log("Filtering files to get fileparts only");

            // filter files
            for(File file : allFiles){
                String fullName = file.getName(); //  name, format, 'part', partnum [no number on purpose]
                String nameParts[] = fullName.split("\\.");
                log("part 0: " + nameParts[0]);
                //skip files not made of parts (they have name, format, part, partnum) and that are duplicates
                    if(nameParts.length != 4){
                        continue;
                    }

                    if( (nameParts[0] + "." + nameParts[1]).equals(filename) ){
                        files.add(file);
                }
            }

            // clean redundant list
            allFiles = null;

            log("Sorting file parts");
            log("Listing sorted parts");
            // sort files
            sortFileParts(files);
            for(File file : files){
                log(file.getAbsolutePath());
            }
            log("Listing sorted parts done");

            prompt("File merging initialization done, saving merged file");

            FileUtils.saveMergedFile(files, fileUtils.downloadedFilesPath + "\\" + filename);

            prompt("Cleaning files");

            for(int i = files.size() - 1; i >= 0 ; i--){

                File tempFile = files.get(i);
                files.remove(i);

                if(tempFile.delete()){
                    prompt("Deleted: " + tempFile.getName());
                }else{
                    prompt("Failed to delete: " + tempFile.getName());
                }
            }

            prompt("File cleanup done");

    }

    private static void sortFileParts(ArrayList<File> files) {
        int n = files.size();

        // bubble sort
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++){

                // partnums to separate variables
                int f_j = Integer.parseInt( files.get(j).getName().split("\\.")[3] );
                int f_j2 = Integer.parseInt( files.get(j+1).getName().split("\\.")[3] );

                if (f_j > f_j2) {
                    Collections.swap(files, j, j+1);
                }
            }

        log("File parts sorted");
    }

    /*
        private static void sendInitMessage(int port, String ip, String message) throws IOException {
            // avoiding passing static variable to a thread
            FileUtils tempFileUtils = new FileUtils(fileUtils.rootPath.toAbsolutePath().toString());
            new Thread(new BasicRequestThread(port, ip, tempFileUtils, message)).start();
        }
    */

    private static Thread sendInitMessage(int port, String ip, String message) throws IOException {
        FileUtils tempFileUtils = new FileUtils(fileUtils.rootPath.toAbsolutePath().toString());         // avoiding passing static variable to a thread
        Thread thread = new Thread(new BasicRequestThread(port, ip, tempFileUtils, message));
        thread.start();
        return thread;
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

        //  0            1               2          3
        //  server port, home_directory, listFiles, hosts...
        //  0            1               2     3     4
        //  server port, home_directory, pull, name, hosts..
        //  server port, home_directory, push, name, hosts..
        //  0            1               2         3     4             5
        //  server port, home_directory, continue, name, amount_parts, hosts...

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
        }else if(commandType.equals("continue")){
            filename = args[3].trim();
            continuePartsOverallAmount = Integer.parseInt(args[4]);
            i += 2;
        }

        // parse hosts list
        for(; i < args.length; i++){
            String[] hostInfo = args[i].trim().split(":");
            String IP_parsed = hostInfo[0];
            int port_parsed = Integer.parseInt(hostInfo[1]);

            hostsList.add(new HostData(port_parsed, IP_parsed));
        }


    }

    private static void showHelp() {

        System.out.println("===============MANUAL===============\n" +
                "Arguments syntax\n" +
                "server port, home_directory, listFiles, hosts...\n" +
                "server port, home_directory, pull, name, hosts..\n" +
                "server port, home_directory, push, name, hosts..\n" +
                "server port, home_directory, continue, name, amount_parts, hosts...\n" +
                "man / help\n+" +
                "Example: 10007 \"Z:\\GitHubLearning\\Learning\\Java\\bin\\FileTransfer\\Files1\" pull testPic.jpg 0.0.0.0:10005 0.0.0.0:10006 \n+" +
                "=============MANUAL END=============\n");
    }

}


