package FileTransfer;

import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

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

            return "Socket: " + socket + "\n" + fileUtils.getFileListString() + "\r\n";
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
                return "File not found";
            }
        }


        if(request.startsWith("%FILEBEGINNING%")){
            request = request.replace("%FILEBEGINNING%", "");
            request = request.replace("%FILEEND%", "").trim();

            if(FileUtils.parseAndSaveFile(fileUtils.downloadedFilesPath.toAbsolutePath().toString(),
                    request)){
                return "File received. Socket: " + socket;
            }else {
                System.out.println("Failed to receive a file, sending a new pull request");

                Scanner scanner = new Scanner(request);
                request = scanner.nextLine().trim();

                return "%PULL% " + request;
            }

        }

        return null;
    }

}
