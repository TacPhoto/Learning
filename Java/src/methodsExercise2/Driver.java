package methodsExercise2;

public class Driver
{
    public static void main(String[] args)
    {
        // 1
        {
            Triangle triangle1 = new Triangle(4.f);
            System.out.println("Area: " + triangle1.getArea());
            System.out.println("Volume: " + triangle1.getVolume());
            line();
        }
        // 2
        {
            String str = "some text here";
            char[] string = new char[str.length()];

            for(int i = 0; i < str.length(); i++)
                string[i] = str.charAt(i);

            Cypher szyfr = new Cypher(1, string);
            System.out.println(szyfr.show());
            Cypher szyfr2 = new Cypher(0, string);
            System.out.println(szyfr2.show());

            line();
        }
        // 3
        {
            Dzem fjagodowy = new Dzem("fjagodowy", 300.);
            Dzem truskawkowy = new Dzem("truskawkowy");
            Dzem noname = new Dzem(400.);
            System.out.println("fjagodowy: " + fjagodowy.smak + "  " + fjagodowy.waga);
            System.out.println("fjagodowy: " + truskawkowy.smak + "  " + truskawkowy.waga);
            System.out.println("fjagodowy: " + noname.smak + "  " + noname.waga);
            line();
        }

        //4
        {
        Osiol osiol = new Osiol(.0000001); //osiol nawdychal sie helu i jest baaaardzo lekki
        //Osiol osiol = new Osiol(.001); //osiol nawdychal sie helu i jest baaaardzo lekki
        osiol.dodajBalon();
        osiol.dodajBalon();
        osiol.dodajBalon();
        osiol.dodajBalon();
        osiol.dodajBalon();
            if(osiol.czyLata())
            System.out.println("Ja latam!!!");
        }
    }


    public static void line()
    {
        System.out.println("----------------------------------------------");
    }
}
