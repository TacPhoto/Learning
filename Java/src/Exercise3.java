import java.util.Scanner;

public class Exercise3
{
    public static void main(String[] args)
    {
        float someFloat = 2.7f;
        double someDouble = 3.0;
        char einenLiterken = 'c';
        boolean boolIstnienia= false;
        short krotki = 1;
        byte bit = 0b0001;
        int someInt = 20;

        Scanner input = new Scanner(System.in);

        if(someInt > 0)
        {
            System.out.println("btw it's bigger than 0");
        }
        else
        {
            System.out.println("it's less or equal to 0");
        }

        line();

        System.out.println(divide(4,5));
        System.out.println(divide(4.0, 5.0));
        System.out.println(divide(4, 5.0));
        System.out.println(divide(4.0, 5));

        System.out.println("Enter sth, we'll divide it");
        someInt = input.nextInt();
        System.out.println("Again");
        int someNewInt = input.nextInt();
        System.out.println(divide(someInt, someNewInt));

        System.out.println("Enter sth");
        someInt = input.nextInt();
        System.out.println("Again");
        someNewInt = input.nextInt();
        System.out.print("Got:");
        System.out.println(someInt + someNewInt);

        line();

        boolean czyPada = true;
        boolean czySwieciSlonce = false;

        try
        {
            System.out.println("Czy pada? (true/false)");
            czyPada = input.nextBoolean();
        }
        catch(Exception e){}

        try
        {
            System.out.println("Czy swieci slonce? (true/false)");
            czySwieciSlonce = input.nextBoolean();
        }
        catch(Exception e){}


        if(czyPada)
        {
            if(czySwieciSlonce)
            {System.out.println("Widac tecze");}
            else
            {System.out.println("Jest pochmurno i pada");}
        }
        else
        {
            if(czySwieciSlonce)
            {System.out.println("Piekny sloneczny dzien, wiec siedze w domu");}
            else
            {System.out.println("Jest pochmurno ale chociaż nie pada");}

        }

        line();

        System.out.println("Podaj liczbe");
        String sth = " ";
        sth = input.next();
        double num = Float.valueOf(sth);

        if( num <=- 2) System.out.println("Y");
        else if( num < -1 ) System.out.println("YZ");
        else if( num < 1) System.out.println("XYZ");
        else if( num < 2) System.out.println("YZ");
        else System.out.println("Z");
    }


    public static void line()
    {
        System.out.println("------------------------------------------");
    }


    public static int divide(int a, int b)
    {
        if(b == 0)
        {
            System.out.println("Nice try");
            return 0;
        }
        return a/b;

    }


    public static double divide(double a, double b)
    {
        if(b == 0.0)
        {
            System.out.println("Nice try");
            return 0.0;
        }
        return a/b;
    }
}
