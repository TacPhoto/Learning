public class IsPalindrome
{
    public static void main(String[] args)
    {
        char[] word = {'a', 'b', 'c', 'b', 'a'};
        //char[] word = {'a', 'b', 'c'};
        int len = word.length;
        boolean flag = true;

        for(int i = 0; i < len / 2; i++)
        {
            if(word[i] != word[len - i - 1])
                flag = false;
        }

        System.out.println(flag);
    }
}
