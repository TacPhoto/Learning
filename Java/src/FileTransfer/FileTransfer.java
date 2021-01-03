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

        // TODO: ask host if he has a file

        // TODO:  DOWNLOAD FILE IN PARTS with hosts specified
        //  - ask host for part of a file
        //  - when have all parts, merge them and delete redundant files

        // TODO: DOWNLOAD FILE IN PARTS with hosts not specified
        //  - create list of hosts paired with files they have
        //  - count hosts which have desired file
        //  - save temp file with final MD5, file name and number of all parts
        //  - ask each host for a part of a file
        //  - check if all parts are downlaoded. if not, reask for them, if yes, merge them

        // TODO: RUN COMMAND TO CONTINUE DOWNLOADING FILE
        //  - it is based on file with final MD5, file name and number of all parts
        //  - simply asks hosts for missing parts and then merges them


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


