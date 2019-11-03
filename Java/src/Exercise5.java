import java.util.Scanner;

public class Exercise5
{
    public static void main(String [] args)
    {

        ////////// I
        int lev = 0;
        Scanner scan = new Scanner(System.in);

        System.out.println("Podaj predkosc wiatru");
        float speedKM = scan.nextFloat();
        System.out.println(speedKM);
        {
            if(speedKM > 117.)
            {
                lev = 12;
            }
            else if(speedKM > 103.)
            {
                lev = 11;
            }
            else if(speedKM > 88.)
            {
                lev = 10;
            }
            else if(speedKM > 76.)
            {
                lev = 9;
            }
            else if(speedKM > 63.)
            {
                lev = 8;
            }
            else if(speedKM > 51.)
            {
                lev = 7;
            }
            else if(speedKM > 40.)
            {
                lev = 6;
            }
            else if(speedKM > 30.)
            {
                lev = 5;
            }
            else if(speedKM > 19.)
            {
                lev = 4;
            }
            else if(speedKM > 12.)
            {
                lev = 3;
            }
            else if(speedKM > 7.)
            {
                lev = 2;
            }
            else if(speedKM > 1.)
            {
                lev = 1;
            }
            else if (speedKM < 1.)
            {
                lev = 0;
            }
        }

        switch(lev)
        {
            case 0:
            {
                System.out.println("0 w skali Beufortea");
                System.out.println("Morze jest gladkie, dym unosi sie pionowo");
                break;
            }
            case 1:
            {
                System.out.println("1 w skali Beufortea");
                System.out.println("Zmarszczki na wodzie, ruch powietrza lekko wplywa na dym");
                break;

            }
            case 2:
            {
                System.out.println("2 w skali Beufortea");
                System.out.println("Male fale, szelest lisci");
                break;

            }
            case 3:
            {
                System.out.println("3 w skali Beufortea");
                System.out.println("Duze falki, liscie i galazki w stalym ruchu");
                break;
            }
            case 4:
            {
                System.out.println("4 w skali Beufortea");
                System.out.println("Male, spienione fale, slychac plusk. Galezie zaczynaja sie kolysac");
                break;
            }
            case 5:
            {
                System.out.println("5 w skali Beufortea");
                System.out.println("Szum morza przypomina pomruk, wiatr gwizdze, male drzewa kolsyza sie");
                break;
            }
            case 6:
            {
                System.out.println("6 w skali Beufortea");
                System.out.println("Tworza sie grzywacze, dluga wysoka fale. Galezie w ruchu, wiatr swiszczy");
                break;
            }
            case 7:
            {
                System.out.println("7 w skali Beufortea");
                System.out.println("Morze burzy sie, piana uklada sie w pasma, drzewa w ruchu");
                break;
            }
            case 8:
            {
                System.out.println("8 w skali Beufortea");
                System.out.println("Umiarkowane duze fale z poprzerywanymi obracajacymi sie grzbietami, pasma piany. Lamia sie galazki");
                break;
            }
            case 9:
            {
                System.out.println("9 w skali Beufortea");
                System.out.println("Bardzo duze fale, grzbiety zaczynaja sie zawijac, znaczne bryzgi");
                break;
            }
            case 10:
            {
                System.out.println("10 w skali Beufortea");
                System.out.println("Wielkie fale, biale morze. Drzewa sa wyrywane z korzeniami");
                break;
            }
            case 11:
            {
                System.out.println("11 w skali Beufortea");
                System.out.println("Nadzwyczaj wielkie fale, znaczna czesc konstrukcji zniszczona");
                break;
            }
            case 12:
            {
                System.out.println("12 w skali Beufortea");
                System.out.println("Olbrzymie fale, morze cale pokryte bryzgami. Masowe zniszczenia konstrukcji");
                break;
            }
            default:
            {
                break;
            }

        }

        ////////// III
        {
            System.out.println("Podaj int n (miesiac)");
            int n = scan.nextInt();
            System.out.println("Podaj int d (dzien)");
            int d = scan.nextInt();

            int t = 0;
            do{
                System.out.println("Podaj liczbe calkowita z zakresu -20 do 40");
                t = scan.nextInt();
            }while(t >40 | t < -20);

            int pora = 3; // 0wiosna, 1lato, 2jesien, 3zima. domyslnie zima
            if(n >=3 && n<=6) //czy wiosna
            {
                n = 0;
                if(n == 3 && d <= 20) pora = 3;
                if(n == 6 && d >= 21) pora = 1;
            }
            else if(n >= 7 && n <=9) //czy lato
            {
                pora = 1;
                if(n == 9 && d >= 23) pora = 2;
            }
            else if(n >=10)
                pora = 2;
            if(n == 12 && d >= 22) pora = 3;

            switch(pora)
            {
                case 3:
                {System.out.println("Ponizej 0");}
                break;
                case 0:
                {System.out.println("5 do 10 stopni");}
                break;
                case 1:
                {System.out.println("powyzej 15 stopni");}
                break;
                case 2:
                {System.out.println("5 do 10 stopni");}
                break;
            }
        }

    }
}
