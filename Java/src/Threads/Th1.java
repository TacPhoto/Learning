package Threads;

class NewThread extends Thread{

}


class NewThreadImplRunnable implements Runnable{

    private String name;
    Thread t;

    NewThreadImplRunnable(String name) {
        this.name = name;
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run() {
        System.out.println("Launched a new thread: " + name);

        try {
            System.out.println("Thread: " + name + " is sleeping");
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Thread " + name + " finished");
    }
}


public class Th1 {

    public static void main(String[] args){

        Thread t = Thread.currentThread();
        System.out.println(t);

        t.setName("Some Name");
        System.out.println(t);

        new NewThreadImplRunnable("One");

        try{
            System.out.println("Main thread sleeps");
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }

}
