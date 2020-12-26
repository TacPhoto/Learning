package FileTransfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

import static FileTransfer.Logger.log;

public class FileUtils {
    public Path rootPath;
    public Path localFilesPath;
    public Path downloadedFilesPath;
    private ArrayList<FileData> localFileList;

    public FileUtils(String rootPath){
        this.rootPath = Paths.get(rootPath);
        this.localFilesPath = Paths.get(rootPath, "local\\");
        this.downloadedFilesPath = Paths.get(rootPath, "received\\");
        this.localFileList = new ArrayList<FileData>();
    }

    public static Boolean parseAndSaveFile(String downloadedFilesPath, String request) throws NoSuchAlgorithmException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(request);
        String filename = null;
        String checkSum = null;
        //todo fix it, this method is executed twice for some reason

        int i = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();

            filename = (i == 0) ? line.trim() : filename;
            checkSum = (i == 1) ? line.trim() : checkSum;

            if(i > 1){
                stringBuilder.append(line);
            }

            i++;
        }

        String parsedBase64File = stringBuilder.toString();

        byte[] decodedFile = Base64.getDecoder().decode(parsedBase64File.getBytes(StandardCharsets.UTF_8));

        Path filePath = Paths.get(downloadedFilesPath, filename);

        if(new File (filePath.toAbsolutePath().toString()).exists()){
            i = 0;

            Path tempPath = Paths.get(downloadedFilesPath, filename);
            String tempFilename = filename;

            while(new File (tempPath.toAbsolutePath().toString()).exists()) {
                tempFilename = i + filename;
                tempPath = Paths.get(downloadedFilesPath, tempFilename);
                i++;
            }
            filePath = tempPath;
        }

        Files.write(filePath, decodedFile);

        String newCheckSum = FileData.calculateCheckSum(filePath);

        if(!newCheckSum.equalsIgnoreCase(checkSum.trim())) {
            log("OLD CHECKSUM: " + checkSum);
            log("NEW CHECKSUM: " + newCheckSum);

            new File(filePath.toAbsolutePath().toString()).delete();

            return false;
        }


        return true;
    }


    public static String fileToBase64String(FileData fileData) throws IOException {
        File file = fileData.file;
        FileInputStream fileInputStream = new FileInputStream(file);

        byte fileBytes[] = new byte[(int) file.length()];
        fileInputStream.read(fileBytes);

        String s = Base64.getEncoder().encodeToString(fileBytes);

        return s;
    }


    public boolean updateLocalFileList() throws IOException, NoSuchAlgorithmException {
        localFileList.clear();

        String[] localFilePaths = (new File(localFilesPath.toAbsolutePath().toString())).list();

        if(localFilePaths == null){
            log("no files");
            return false;
        }

        for (String file : localFilePaths){
            log(file);
            FileData fileData = new FileData(Paths.get(localFilesPath.toString(), file));
            localFileList.add(fileData);
        }

        return true;
    }


    public String getFileListString(){
        StringBuilder fileListStringBuilder = new StringBuilder();

        for (FileData fileData : localFileList){
            fileListStringBuilder.append(fileData.toString() + "\n");
        }

        String s = fileListStringBuilder.toString();

        return s;
    }


    public void updateCheckSums() throws IOException, NoSuchAlgorithmException {
        for (FileData fileData : localFileList){
            fileData.updateCheckSum();
        }
    }


    public FileData getFile(String name){
        for (FileData fileData : localFileList){
            if(fileData.file.getName().equals(name))
            {
                return fileData;
            }
        }

        return null;
    }

    public FileData getFile(int index){
        return localFileList.get(index);
    }

}
