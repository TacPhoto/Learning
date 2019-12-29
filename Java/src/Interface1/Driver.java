package Interface1;

public class Driver
{
    public static void main(String[] args)
    {
    Print test = new Print();

    System.out.println(test.someText());
    System.out.println(test.someText("something"));

    test.sayHello();

    }
}
