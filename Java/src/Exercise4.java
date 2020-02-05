import java.util.Scanner;

public class Exercise4
{
    public static void main(String[] args)
    {
    Scanner scan = new Scanner(System.in);
    System.out.println("Podaj wynik procentowy (uzywaj przecinka, nie kropki)");
    float score = scan.nextFloat();

    System.out.println("Ocena: ");
    if(score > 87.5f)
        System.out.print("5");
    else if(score > 81.25f)
        System.out.print("4.5");
    else if(score > 75.f)
        System.out.print("4");
    else if(score > 62.6f)
        System.out.print("3.5");
    else if(score > 50.f)
        System.out.print("3");
    else
        System.out.print("2");

    line();

    System.out.println("Podaj nr miesiaca");

    int losIntos = scan.nextInt();
    System.out.println(losIntos);

    switch(losIntos)
    {
        case 1:
        {System.out.println("Styczen"); break;}
        case 2:
        {System.out.println("Luty"); break;}
        case 3:
        {System.out.println("Marzec"); break;}
        case 4:
        {System.out.println("Kwiecien"); break;}
        case 5:
        {System.out.println("Maj"); break;}
        case 6:
        {System.out.println("Czerwiec"); break;}
        case 7:
        {System.out.println("Lipiec"); break;}
        case 8:
        {System.out.println("Sierpien"); break;}
        case 9:
        {System.out.println("Wrzesien"); break;}
        case 10:
        {System.out.println("Pazdziernik"); break;}
        case 11:
        {System.out.println("Listopad"); break;}
        case 12:
        {System.out.println("Grudzien"); break;}
        default:
        {System.out.println("Podana wartosc nie odpowiada zadnemu miesiacowi");}
    }
    line();

    System.out.println();
    for(int i = 0; i++<10;)
    {
        System.out.print(i + " ");
    }
    line();

    System.out.println("Podaj liczbe calkowita");
    losIntos = scan.nextInt();

    for(int i = 0; i++ < losIntos;)
    {
        System.out.println(i + " * " + losIntos + " = " + i*losIntos);
    }
    line();

    System.out.println("Podaj liczbe calkowita");
    losIntos = scan.nextInt();
    byte bitte = (byte) losIntos;

    for(int i = 16; i > 0; i--)
    {
        if((bitte & 0b1<<i) != 0b0)
        {
            System.out.print(0b1 << i);
            System.out.print("   ");
        }
        else
            System.out.print("0   ");
    }
    System.out.println();
    line();

    int i = 4;
    while(i == 5)
    {
        System.out.println("To sie nigdy nie wyswietli");
    }
    do
    {
        System.out.println("Ale to juz sie wyswietli");
    }while(i == 5);
    line();

    float n = 1.f;
    for(i = 0; i++<10;)
    {
        n *= 2.f;
        System.out.println(1.f / n);
    }
    line();

    for(i = 1; i++<5;)
    {
        for(int j = 1; j++ < i;)
        {
            System.out.print("*");
        }
        System.out.println();
    }
}


    public static void line()
    {
        System.out.println("------------------------------------------");
    }
}
