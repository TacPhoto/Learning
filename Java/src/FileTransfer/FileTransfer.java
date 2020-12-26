package FileTransfer;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import static FileTransfer.Logger.log;

public class FileTransfer {

    static ArrayList<HostData> hostsList = new ArrayList<HostData>();

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        // TODO: Parse cmdline:
        //  server port, home_directory, listFiles, hosts...
        //  server port, home_directory, pull, name, hosts..
        //  server port, home_directory, push, name, hosts..
        //  man / help

        // TODO:  DOWNLOAD FILE IN PARTS
        //  - send number of part to get and amount of all parts
        //  - save each part as a separate file
        //  - validate each part separately. retry if broken
        //  - when all are downloaded, merge them
        //  - calculate md5 and compare

        int serverPort = 10005;

        parseLaunchArguments(args);

        String rootPath = "Z:\\GitHubLearning\\Learning\\Java\\src\\FileTransfer\\Files1";
        FileUtils fileUtils = new FileUtils(rootPath);
        fileUtils.updateLocalFileList();
        System.out.println(fileUtils.getFileListString());



        Thread serverDriver = new Thread(new ServerDriver(serverPort, fileUtils));
        serverDriver.start();


        System.out.println("TEST/////////////////////////////////////////");
    }

    private static void parseLaunchArguments(String[] args) {

        if(args[0].equals("man") || args[0].equals("help")){
            showHelp();
            return;
        }

        int port = Integer.parseInt(args[0]);
        String commandType = args[1].trim();


    }

    private static void showHelp() {
    }
}


