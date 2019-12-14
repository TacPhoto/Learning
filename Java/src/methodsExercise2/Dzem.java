package methodsExercise2;

public class Dzem
{
    String smak;
    double waga;

    public Dzem(String smak, double waga)
    {
        this.smak = smak;
        this.waga = waga;
    }

    public Dzem(String smak)
    {
        this.smak = smak;
        this.waga = 100.0;
    }

    public Dzem(double waga)
    {
        this.smak = "No name";
        this.waga = waga;
    }

    public String getSmak() {
        return smak;
    }

    public double getWaga() {
        return waga;
    }


}
