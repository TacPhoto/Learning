package Exercise1;

public class Vehicle {
    private String marka;

    public Vehicle(String marka) {
        this.marka = marka;
    }

    String show()
    {
        String result = "Marka: " + marka;
        System.out.println(result); //debug
        return result;
    }
}
