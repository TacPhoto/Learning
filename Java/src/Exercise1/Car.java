package Exercise1;

public class Car extends Vehicle
{
    private int vin;
    private int doorsAmount;

    public Car(String brand, int vin, int doorsAmount) {
        super(brand);
        this.vin = vin;
        this.doorsAmount = doorsAmount;
    }

    String show()
    {
        String result = "Brand: " + super.show() + " vin: " + vin + " Doors: " + doorsAmount;
        System.out.println(result); //debug
        return result;
    }
}
