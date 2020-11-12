class Exercise2
{
    public static void main(String[] args)
    {
        System.out.println("Karol Kowalczyk, S20240");
        line();

        //System.out.println(num);
        int num = 4;
        System.out.println(num);
        line();

        int a = 4, b = 7, c = 2; //I'd use an array but I'm probably supposed do it as told to. We'll bypass it later

        printSorted(a, b, c);
        line();

        //int foo = 10.0;
        int foo = (int) 10.0;

        int bar = 'b';
        System.out.println("b: " + b);
        line();

        System.out.println("Przypisanie w przypadku zad 3 bylo proba przypisania zmiennej jednego typu do zmiennej innego typu.\n" +
                "W zad4 probowalismy przypisac znak do zmiennej przeznaczonej dla liczb calkowitych i jest to calkowicie poprawne,\n" +
                "a to stad ze znaki przechowujemy w formie wartosci liczbowej. W zaleznosci od zastosowanego kodowania wartosci\n" +
                "i odpowiadajace im znaki moga sie roznic jednak zasada jest z grubsza ta sama");
    }


    public static void printSorted(int a, int b, int c)
    {
        int[] arr = {a, b, c}; //the bypass mentioned before

        for(int j = 0; j < ( arr.length - 1 ); j++)
        {
            for (int i = 0; i < (arr.length - 1); i++)
            {
                if (arr[i] > arr[i + 1])
                {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }

        for (int val: arr) System.out.println(val);
    }


    public static void line()
    {
        System.out.println("---------------------------------------");
    }
}