public class exercise11
{
    public static void main(String[] args)
    {
        int[] arr = {1, 3, 5, 2, 4, 8};
        //int[] arr = {1, 2, 3, 3, 2 ,1};
        //int[] arr = {1, 2, 3, 4, 3, 2, 1};

        int len = arr.length;
        int half_len = len / 2;
        boolean flag = true;

        for(int i = 0; i < half_len; i++)
        {
            if(arr[i] != arr[len - i - 1])
                flag = false;
        }

        if(flag)
            System.out.println("true");
        else
            System.out.println("false");
    }
}
