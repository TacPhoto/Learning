package FileTransfer;

import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static FileTransfer.Logger.log;
import static FileTransfer.Logger.prompt;


public class RequestParser {

    static FileUtils fileUtils;
    private Socket socket;

    RequestParser(FileUtils fileUtils, Socket socket){
        this.fileUtils = fileUtils;
        this.socket = socket;
    }

    public String returnMessage(String request) throws IOException, NoSuchAlgorithmException {
        request = request.replace("%REQUESTEND%", "");

        if(request.startsWith("%LISTLOCALFILES%")){
            String list =  new String(fileUtils.getFileListString());

            log("Sending file list: \n" + list);

            return "Socket: " + socket + "\n" + "Files : \n" + list + "\n" +
                     "%CLOSECONNECTION%\r\n" ;
        }

        if(request.startsWith("%DOYOUHAVE%")){
            request = request.replace("%DOYOUHAVE%", "").trim();

            String commandArg[] = request.split(" ");
            String fileName = commandArg[0];
            String checkSum = "";

            if(commandArg.length != 1)
                checkSum = commandArg[1];

            if(fileUtils.getFile(fileName) != null){
                if(fileUtils.getFile(fileName).checkSum.equals(checkSum) || checkSum.equals("")){
                    return "%IHAVE%\r\n";
                }
            }

            return "%NO%\r\n";

        }

        if(request.startsWith("%PULL%")) {
            request = request.replace("%PULL%", "").trim();

            FileData fileData = fileUtils.getFile(request);

            if (fileData != null) {
                String fileContent = FileUtils.fileToBase64String(fileData);
                return ("%FILEBEGINNING%\r\n" +
                        fileData.file.getName() + "\r\n" +
                        fileData.checkSum +"\r\n" +
                        fileContent + "\r\n%FILEEND%\r\n");
            }
            else {
                return "File not found\r\n";
            }
        }

        if(request.startsWith("%PULLPART%")) {
            String command = getFirstStringLine(request);
            command = command.replace("%PULLPART%", "").trim();
            System.out.println("COMMAND:"+command);

            String[] commandArr = command.split(" ");
            int partNum = Integer.parseInt(commandArr[0]);
            int partNumAll = Integer.parseInt(commandArr[1]);
            String fileName = commandArr[2];

            if(partNum > partNumAll){
                return "Requested part number is higher then number of all available parts\r\n";
            }

            FileData fileData = fileUtils.getFile(fileName);

            if (fileData != null) {
                String fileContent = FileUtils.fileToBase64String(fileData, partNum, partNumAll);
                return ("%FILEBEGINNING%\r\n" +
                        fileData.file.getName() + ".part." + partNum + "\r\n" +
                        FileData.calculateCheckSum(fileContent, "base64") +"\r\n" +
                        fileContent + "\r\n%FILEEND%\r\n");
            }
            else {
                return "File not found\r\n";
            }
        }

        if(request.startsWith("%FILEBEGINNING%")){
            request = request.replace("%FILEBEGINNING%", "");
            request = request.replace("%FILEEND%", "").trim();

            if(FileUtils.parseAndSaveFile(fileUtils.downloadedFilesPath.toAbsolutePath().toString(),
                    request)){
                return "File received. Socket: " + socket + "\r\n";
            }else {
                System.out.println("Failed to receive a file, sending a new pull request");

                return "%PULL% " + getFirstStringLine(request) + "\r\n";
            }

        }

        if(!request.equals(""))
            prompt(request);

        return null;
    }

    public static String getFirstStringLine(String s){
        Scanner scanner = new Scanner(s);
        return scanner.nextLine().trim();
    }
}
