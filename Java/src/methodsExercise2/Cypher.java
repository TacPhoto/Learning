package methodsExercise2;

public class Cypher<i>
{
    private int key;
    char[] buf;

    public Cypher(int key, char[] string)
    {
        this.key = key;
        this.buf = string;
    }

    public char[] cipher(char[] buf)
    {
        for (int i = 0; i < buf.length / 2; i++)
        {
            char temp = buf[i];
            buf[i] = buf[buf.length - i - 1];
            buf[buf.length - i - 1] = temp;
        }

        return buf;
    }

    public char[] encode()
    {
        return cipher(buf);
    }

    public char[] decode()
    {
        return cipher(buf);
    }

    public char[] show()
    {
        if(key >= 1)
            return decode();
        else
            return encode();
    }
}
