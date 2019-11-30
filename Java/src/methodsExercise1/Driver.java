package methodsExercise1;

public class Driver {
    public static void main(String[] args)
    {
        {
            xoSquare(7, 0);
            line();
        }
        {
            int min = 0, max = 100, len = 10;
            float[] arr = new float[len];

            for (int i = 0; i < len; i++)
                arr[i] = (float) (Math.random() * ((max - min) + 1)) + min;

            getMaxMin(arr);

            float mean = meanOfTwo(getMax(arr), getMin(arr));
            System.out.print("Mean on Max and Min: " + mean);

            int count = 0;
            for (int i = 0; i < len; i++)
            {
                if (arr[i] < mean)
                    count++;
            }

            float[] newArr = new float[count];
            int iNew = 0;
            for (int iFirst = 0; iFirst < len; iFirst++)
                if (arr[iFirst] < mean)
                    newArr[iNew++] = arr[iFirst];

            line();

            for (int i = 0; i < count; i++)
                System.out.println((i + 1) + "   " + newArr[i]);
            }
        {
            Triangle triangle = new Triangle(4.7f, 5.3f); //Hodor Hodor Hodor

            triangle.showTriangleArea();


        }
        {
            int someInt = 23;
            char[] bin;

            bin = convertToBIN(someInt);
        /*for(int i = 0; i < bin.length; i++)
            System.out.print(bin[i] + "   ");*/
            line();
        }
        {
        char[][] arr = new char[3][5];
        int min = 'A', max = 122, rand = 0;

        for (int row = 0; row < arr.length; row++)
            for (int col = 0; col < arr[0].length; col++) {
                rand = (int) (Math.random() * ((max - min) + 1)) + min;
                arr[row][col] = (char) rand;
            }

        System.out.println(areSame(arr));
            }

    }


    public static void xoSquare(int len, int starter) {
        char arr[][] = new char[len][len];
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr.length; col++) {
                if(starter == 0) {
                    if (col % 2 == 0 && row % 2 == 0)
                        System.out.print((arr[row][col] = 'x') + " \t");
                    else System.out.print((arr[row][col] = 'o') + "\t");

                }
                else
                {
                    if (col % 2 == 0 && row % 2 == 0)
                        System.out.print((arr[row][col] = 'o') + " \t");
                    else System.out.print((arr[row][col] = 'x') + "\t");
                    break;
                }
            }
            System.out.println();
        }
    }


    public static void line()
    {
        System.out.print("\n------------------------------------------------\n");
    }


    public static float getMax(float[] arr)
    {
        float temp = arr[0];

        for(int i = 0; i < arr.length; i++)
        {
            if(temp < arr[i])
            {
                temp = arr[i];
            }
        }

        return temp;
    }


    public static float getMin(float[] arr)
    {
        float temp = arr[0];

        for(int i = 0; i < arr.length; i++)
        {
            if(temp > arr[i])
            {
                temp = arr[i];
            }
        }

        return temp;
    }


    public static void getMaxMin(float[] arr)
    {
        System.out.println("Max: " + getMax(arr));
        System.out.println("Min " + getMin(arr));
    }


    public static float meanOfTwo(float a, float b)
    {
        return (a + b) / 2.f;
    }


    public static char[] convertToBIN(int dec) {
        int i = 0;
        char[] hex = new char[10];

        while (dec != 0) {
            int remainder = 0;
            remainder = dec % 16;

            if (remainder < 10)
                hex[i++] = (char) (remainder + 48);
            else
                hex[i++] = (char) remainder;

            dec = dec / 16;
        }

        char temp[] = new char[6];
        char[] bin = new char[10 * 6];
        int binCount = 0;
        for (int mainCount = 0; mainCount < 10; mainCount++) {
            for (int convCount = 0; convCount < 6; convCount++) {
                bin[binCount++] = (char) ((int) hex[mainCount] & 0b1 << convCount);
            }
        }


        return bin;
    }


    public static boolean areSame(char[][] arr)
    {
        int numRow = arr.length; int numCol = arr[0].length;


        for(int i = 0; i < numCol- 2; i++)
        {
            int first = (int)(arr[numRow/2][i]);
            int second = (int)(arr[numRow/2][i+1]);
            int third = (int)(arr[numRow/2][i+2]);
            if(( first < second ) & (second < third))
                return true;
            if(( third < second ) & (third < second))
                return true;
        }


        for(int i = 0; i < numRow- 2; i++)
        {
            int first = (int)(arr[i][numCol/2]);
            int second = (int)(arr[i+1][numCol/2]);
            int third = (int)(arr[i+2][numCol/2]);
            if(( first < second ) & (second < third))
                return true;
            if(( third < second ) & (third < second))
                return true;
        }

        return false;


    }
}
