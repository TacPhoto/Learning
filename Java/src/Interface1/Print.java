package Interface1;

public class Print implements PrintInterface
{
    public String someText()
    {
        return "Some text";
    }

    public String someText(String str)
    {
        return "Some text: " + str;
    }

    @Override
    public void sayHello()
    {
    System.out.println("Hello");
    }
}
