package Stack;

import java.util.Scanner;

public class StackMain
{
    public static void main(String[] args)
    {
        Stack stack = new Stack();
        Scanner scan = new Scanner(System.in);

        int buf = 1;

        System.out.println("Use 0 to finish execution\nMaximum length is " + stack.maxLength());

        while (buf != 0)
        {
            System.out.println("1 - push, 2 - pop, 3 - get last, 4 - remove last, 5 - show length\n" +
                    "A full stack will be printed every time");
            buf = scan.nextInt();

            switch(buf)
            {
                case 0:
                    System.out.println("Program will be terminated after the loop");
                case 1:
                    System.out.println("Enter the argument");
                    buf = scan.nextInt();
                    stack.push(buf);
                    break;
                case 2:
                    System.out.println("Popped: " + stack.pop());
                    break;
                case 3:
                    System.out.println("Last: " + stack.getLast());
                    break;
                case 4:
                    System.out.println("Removed last record");
                    stack.removeLast();
                    break;
                case 5:
                    System.out.println("Length: " + stack.length());
                    break;
                default:
                    System.out.println("Nothing was changed");
                    break;
            }

            System.out.println("----------------------------------");
            stack.print();
            System.out.println("----------------------------------");
        }
    }
}
