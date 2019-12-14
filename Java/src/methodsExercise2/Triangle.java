package methodsExercise2;

public class Triangle
{
    private float edge_len;
    private float area;

    public Triangle(float edge_len) {
        this.edge_len = edge_len;
        this.area = edge_len * edge_len * 0.433f;
    }


    public float getArea() {
        return area;
    }


    public float getVolume()
    {
        return area * 5.f;
    }
}
