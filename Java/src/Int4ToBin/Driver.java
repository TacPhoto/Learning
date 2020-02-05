package Int4ToBin;

public class Driver
{
    public static void main(String[] args) throws Pixel.ZeroesException {
        int r = 7; int g = 5; int b = 1; int a = 6;
        Pixel pixel = new Pixel(r, g, b, a);

        assert ( pixel.get_binary(r, 8).equals(pixel.get_r()) ) : "Binary r does not match get_b";
        assert ( pixel.get_binary(g, 8).equals(pixel.get_g()) ) : "Binary g does not match get_b";
        assert ( pixel.get_binary(b, 8).equals(pixel.get_b()) ) : "Binary b does not match get_b";
        assert ( pixel.get_binary(a, 8).equals(pixel.get_a()) ) : "Binary a does not match get_b";
        //System.out.println("Correct bin:  00000111000001010000000100000110"); //DEBUG
        { Pixel fixed = new Pixel(7, 5, 1, 6); assert (pixel.getInt() == 117768454) : "Wrong pixel value. Possible faulty dec2bin conversion"; }

        System.out.println(pixel.toString());

    }
}
