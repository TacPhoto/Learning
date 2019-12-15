package methodsExercise2;

public class Balon
{
    private float contentVol;
    private float load;

    public Balon()
    {
        float max = 0.009f, min = 005f;
        this.contentVol = clamp((float)Math.random() / 100.f, min, max); // 0.005 to 0.009 m3
    }

    float max(float a, float b)
    {
        return a > b ? a : b;
    }

    float min(float a, float b)
    {
        return a < b ? a : b;
    }

    float clamp(float x, float a, float b)
    {
        return max(a, min(b, x));
    }

    float podajUdzwig()
    {
        return contentVol / (6.f / 7.f); // 6/7g per 0.001m3
    }

}
