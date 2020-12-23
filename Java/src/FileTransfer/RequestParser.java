package FileTransfer;

public class RequestParser {

    static FileUtils fileUtils;

    RequestParser(FileUtils fileUtils){
        this.fileUtils = fileUtils;
    }

    public String returnMessage(String request){
        if(request.equals("%LISTLOCALFILES%")){
            return fileUtils.getFileListString() + "\r\n";
        }


        return null;
    }
}
