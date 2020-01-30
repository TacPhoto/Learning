package LinkedList;


public class Link
{
    public String name;
    public int valueNumeric;

    public Link next;

    public Link(String name, int valueNumeric)
    {
        this.name = name;
        this.valueNumeric = valueNumeric;
    }

    public void display()
    {
        System.out.println(name + "   " + valueNumeric);
    }

    public String toString()
    {
        return name;
    }
}
