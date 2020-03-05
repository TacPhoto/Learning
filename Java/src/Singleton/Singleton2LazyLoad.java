package Singleton;

public class Singleton2LazyLoad {

    public static Singleton2LazyLoad INSTANCE;

    private Singleton2LazyLoad(){
    }

    public static Singleton2LazyLoad getInstance(){
        if(INSTANCE == null)
            INSTANCE = new Singleton2LazyLoad();

        return INSTANCE;
    }
}
