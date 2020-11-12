package Stack;

public class Stack
{
    int[] stack = new int[5]; //will not be dynamic
    int top = 0;

    public void push(int data)
    {
        if(top != stack.length)
        {
            stack[top] = data;
            top++;
        }
        else
            System.out.println("STACK IS FULL, CANNOT EXECUTE");
    }


    public void print()
    {
        for(int i = 0; i < top; i++)
        {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }


    public int pop()
    {
        int data;
        data = stack[top - 1];
        stack[top - 1] = 0;

        if(top != 0)
            top--;

        return data;
    }


    public int getLast()
    {
        return stack[top];
    }


    public void removeLast()
    {
        stack[top] = 0;

        if(top != 0)
            top--;
    }


    public int length()
    {
        int len = 0;
        for(int n : stack)
        {
            if(stack[n] == 0)
                return len;
            len++;
        }
        return len;
    }


    public int maxLength()
    {
        return stack.length;
    }
}
