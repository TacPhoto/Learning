package LinkedList;


public class LinkList
{
    public Link firstLink;

    LinkList()
    {
        firstLink = null;
    }

    public boolean isEmpty()
    {
        return (firstLink == null);
    }

    public void insertFirstLink(String name, int valueNumeric)
    {
        Link newLink = new Link(name, valueNumeric);

        newLink.next = firstLink;
        firstLink = newLink;
    }

    public Link removeFirst()
    {
        Link linkReference = firstLink;

        if(!isEmpty())
            firstLink = firstLink.next;
        else
            System.out.println("Empty Linked List");

        return linkReference;
    }

    public void display()
    {
        Link theLink = firstLink;

        while(theLink != null)
        {
            theLink.display();

            System.out.println("Next Link: " + theLink.next + "\n");

            theLink = theLink.next;
        }
    }

    public Link find(String name)
    {
        Link theLink = firstLink;

        if(!isEmpty()) {
            while (theLink.name != name)
                if (theLink.next == null)
                    return null;
                else
                    theLink = theLink.next;
        }
        else
            System.out.println("Empty Linked List");

        return theLink;
    }

    public Link removeLink(String name)
    {
        Link currentLink = firstLink;
        Link previousLink = firstLink;

        while(!currentLink.name.equals(name))
        {
            if (currentLink.next == null)
                return null;
            else
            {
                previousLink = currentLink;
                currentLink = currentLink.next;
            }
        }

        if(currentLink == firstLink)
            firstLink = firstLink.next;
        else
            previousLink.next = currentLink.next;

        return currentLink;

    }
}