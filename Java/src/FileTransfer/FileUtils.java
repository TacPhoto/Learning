package FileTransfer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static FileTransfer.Logger.log;

public class FileUtils {
    public Path rootPath;
    public Path localFilesPath;
    public Path downloadedFilesPath;
    private ArrayList<FileData> localFileList;

    public FileUtils(String rootPath){
        this.rootPath = Paths.get(rootPath);
        this.localFilesPath = Paths.get(rootPath, "local");
        this.downloadedFilesPath = Paths.get(rootPath, "received");
        this.localFileList = new ArrayList<FileData>();
    }

    public boolean updateLocalFileList() throws IOException, NoSuchAlgorithmException {
        localFileList.clear();

        String[] filePaths = (new File(localFilesPath.toAbsolutePath().toString())).list();

        if(filePaths == null){
            log("no files");
            return false;
        }

        for (String file : filePaths){
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
