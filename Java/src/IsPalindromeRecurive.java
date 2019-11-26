public class IsPalindromeRecurive
{
    public static void main(String[] args)
    {
        char[] word = {'a', 'b', 'c', 'b', 'a'};
        //char[] word = {'a', 'b', 'c'};

        System.out.println(isPalindrome(word));
    }


    static boolean isPalindrome(char[] word)
    {
        int i = word.length;

        if (i == 0)
            return true;

        return isPalindromeBackend(word, 0, i - 1);
    }


    static boolean isPalindromeBackend(char word[], int left, int right)
    {
        if (left == right)
            return true;

        if (word[left] != word[right])
            return false;

        if (left < right + 1)
            return isPalindromeBackend(word, left + 1, right - 1);

        return true;
    }
}
