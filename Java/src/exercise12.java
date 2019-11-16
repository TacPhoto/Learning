class exercise12
{
    public static void main(String[] args) {
        {
            char arr[] = {'a', 'b', 'c', 'd'};

            int len = arr.length;

            char temp;
            for (int i = 0; i < arr.length / 2; i++) {
                temp = arr[i];

                arr[i] = arr[arr.length - 1 - i];

                arr[arr.length - 1 - i] = temp;
            }


            for (int i = 0; i < len; i++)
                System.out.print(arr[i] + " ");
        } //1
        {
            line();

            int len = 32;
            char[] arr = new char[len];

            int max = 255, min = 0;

            int rand;
            for (int i = 0; i < len; i++) {
                rand = (int) (Math.random() * ((max - min) + 1)) + min;
                arr[i] = (char) rand;
            }

            for (int i = 0; i < len; i++)
                System.out.print(arr[i] + " ");

            line();

            int count = 0;

            for (int i = 0; i < len; i++)
                if (Character.isUpperCase(arr[i]))
                    count++;

            System.out.print("Liczba znakow z przedzialu A do Z:" + count);
            line();
        } //2
        {
            byte[] arr = new byte[5];
            byte[] arr2 = new byte[8];
            int len1 = arr.length, len2 = arr2.length, len3 = len1 + len2;
            int min = 0, max = 128;
            byte[] arr3 = new byte[len3];

            for (int i = 0; i < len1; i++) {
                int rand = (int) (Math.random() * ((max - min) + 1)) + min;
                arr[i] = (byte) rand;
            }

            for (int i = 0; i < len2; i++) {
                int rand = (int) (Math.random() * ((max - min) + 1)) + min;
                arr2[i] = (byte) rand;
            }

            int i = 0;
            while (i < len1) {
                arr3[i] = arr[i];
                i++;
            }

            //System.out.println(); for(int k = 0; k < len1; k++) System.out.print(arr[k] + " ");

            int j = 0;
            while (i + j < len3) {
                arr3[i + j] = arr2[j];
                j++;
            }

            //System.out.println(); for(int k = 0; k < len2; k++) System.out.print(arr2[k] + " ");


            for (i = 0; i < len3 - 1; i++)
                for (j = 0; j < len3 - i - 1; j++)
                    if (arr3[j] > arr3[j + 1]) {
                        byte temp = arr3[j];
                        arr3[j] = arr3[j + 1];
                        arr3[j + 1] = temp;
                    }

            System.out.println();
            for (int k = 0; k < len3; k++) System.out.print(arr3[k] + " ");

            line();
        } //3
        {
            int[] arr = new int[3];
            //1 - 200

            int count = 0;
            for (int number = 1; number < 200; number++) {
                int temp = 0, temp2 = 0;
                for (int i = 1; i <= number / 2; i++) {
                    if (number % i == 0) {
                        temp += i;

                        if (temp == number) {
                            count++;
                            //System.out.println(temp + " " + count);

                            int old_temp = temp;

                            if (count < 3)
                                arr[count] = temp;

                            if (count > 3 && count % 2 == 0) {
                                arr[count - 1] = old_temp;
                                arr[count] = temp;
                            }
                        }
                    }

                }
            }
            for (int i = 0; i < arr.length; i++)
                System.out.print(arr[i] + " ");

            line();
        } //4
        {
            int[] arr = {3, 2, 1, 6, 5, 4};
            int len = arr.length;

            for (int i = 0; i < len - 1; i++)
                for (int j = 0; j < len - i - 1; j++)
                    if (arr[j] > arr[j + 1])
                    {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }

            for (int i = 0; i < len; i++)
                System.out.print(arr[i] + " ");
            line();
        } //5
    }


    public static void line()
    {
        System.out.println("\n---------------------------------");
    }
}