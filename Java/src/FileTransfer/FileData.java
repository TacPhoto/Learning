package FileTransfer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class FileData {
    public Path path;
    public String checkSum;
    public File file;

    public FileData(Path path) throws IOException, NoSuchAlgorithmException {
        this.path = path;
        this.file = new File(path.toAbsolutePath().toString());
        updateCheckSum();
    }

    public void updateCheckSum() throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(path));
        byte[] digest = md.digest();
        checkSum = Base64.getEncoder().encodeToString(digest);
    }

    @Override
    public String toString(){
        return new String(file.getName() + "   " + checkSum);
    }
}
