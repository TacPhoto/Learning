package FileTransfer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class FileData {
    public Path path;
    public String checkSum;
    public String encodedCheckSum;
    public File file;

    public FileData(Path path) throws IOException, NoSuchAlgorithmException {
        this.path = path;
        this.file = new File(path.toAbsolutePath().toString());
        checkSum = calculateCheckSum(path);

    }

    public static String calculateCheckSum(Path path) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(path));
        byte[] digest = md.digest();
        return Base64.getEncoder().encodeToString(digest);
    }


    public static String calculateCheckSum(byte[] bytes) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);
        byte[] digest = md.digest();
        return Base64.getEncoder().encodeToString(digest);
    }

    public static String calculateCheckSum(String string, String mode) throws NoSuchAlgorithmException, IOException {

        byte bytes[] = mode.equals("base64") ?
                Base64.getDecoder().decode(string.getBytes(StandardCharsets.UTF_8)) : string.getBytes();

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);
        byte[] digest = md.digest();
        return Base64.getEncoder().encodeToString(digest);
    }


    @Override
    public String toString(){
        return new String(file.getName() + "   " + checkSum);
    }

    public void updateCheckSum() throws IOException, NoSuchAlgorithmException {
        checkSum = calculateCheckSum(path);
    }
}
