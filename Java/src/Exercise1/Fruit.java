package Exercise1;

public class Fruit
{
    private String name;
    private double mass; //grams

    public Fruit(String name) {
        float min = 100.f;
        float max = 250.f;
        float random = (float) (min + Math.random() * (max - min));
        this.mass = random;

        this.name = name;
    }

    public double getMass() {
        return mass;
    }
}
