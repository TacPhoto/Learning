package FileTransfer;

public class Logger {
    // debug logger, not useful for a typical user
    public static void log(String m){
        System.out.println("log: " + m);
    }

    // release logger
    public static void prompt(String m){
        System.out.println("\nProgram prompt: " + m);
    }
}
