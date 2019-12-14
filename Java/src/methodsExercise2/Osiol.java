package methodsExercise2;

public class Osiol
{
    private double masa; //(kg)
    private Balon[] balony = new Balon[5];
    int stack;

    public Osiol(double masa)
    {
        this.masa = masa;
        this.stack = 0;
    }

    void dodajBalon()
    {
        Balon balon = new Balon();
        this.balony[stack] = balon;
        this.stack++;
    }

    boolean czyLata()
    {
        float load = 0.f;

        for(Balon i: balony)
            if(!(i == null))
                load += i.podajUdzwig();

        if(load / 1000.f > (float)masa)
            return true;
        return false;
    }
}
