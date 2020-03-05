package Singleton;

//based on Effective Java (3rd edition 2018)
public class Singleton {

    public static final Singleton INSTANCE = new Singleton();

    private Singleton(){
        if(INSTANCE != null)
            throw new IllegalStateException("Singleton already constructed");
    }
}
