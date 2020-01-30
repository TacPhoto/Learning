package LinkedList;

import org.w3c.dom.ls.LSOutput;

import java.util.LinkedList;


public class ListDriver
{
    public static void main(String[] args)
    {

        LinkList theLinkedList = new LinkList();

        theLinkedList.insertFirstLink("First", 20);
        theLinkedList.insertFirstLink("Second", 20342);
        theLinkedList.insertFirstLink("BlahBlahBlah", 40);
        theLinkedList.insertFirstLink("QWERTY", 8720);

        theLinkedList.display();
        line();
        theLinkedList.find("Second").display();
        line();
        theLinkedList.removeLink("BlahBlahBlah");
        theLinkedList.display();
    }

    public static void line()
    {
        System.out.println("---------------------------------");
    }
}
