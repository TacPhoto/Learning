package methodsExercise1;

public class Triangle
{
    private float width, height;


    public Triangle(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth()
    {
        return width;
    }


    public float getHeight()
    {
        return height;
    }


    public float[] get2Sizes()
    {
        float[] sizes = new float[2];
        sizes[0] = getWidth();
        sizes[1] = getHeight();

        return sizes;
    }


    public void showTriangleArea()
    {
        float[] sizes = new float[2];
        sizes = get2Sizes();

        System.out.print("Triangle area: " + sizes[0] * sizes[1]);
    }
}
