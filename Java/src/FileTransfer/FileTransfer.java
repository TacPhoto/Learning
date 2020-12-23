package FileTransfer;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static FileTransfer.Logger.log;

public class FileTransfer {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        int serverPort = 10005;

        String rootPath = "Z:\\GitHubLearning\\Learning\\Java\\src\\FileTransfer\\Files1";
        FileUtils fileUtils = new FileUtils(rootPath);
        fileUtils.updateLocalFileList();
        System.out.println(fileUtils.getFileListString());


       TCP_Server server = new TCP_Server(serverPort, fileUtils);
       /*
        while (true) {
            System.out.println("Connected");
            server.start();
        }
        */
        do{
            log("Connected");
            Thread thread = new Thread(server);
            thread.start();
        }while(server.serverSocket != null);

    }
}


