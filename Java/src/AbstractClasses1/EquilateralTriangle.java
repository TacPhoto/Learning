package AbstractClasses1;

public class EquilateralTriangle extends Shape
{
    private float edgeLen;
    private float area;

    public EquilateralTriangle(float edgeLen)
    {
        this.edgeLen = edgeLen;
        this.area = calcArea(edgeLen);
    }

    @Override
    public void showParams()
    {
        System.out.println("Type: EquilateralTriangle  Area: " + area + "   Edge Length: " + edgeLen);
    }

    @Override
    public float getArea()
    {
        return area;
    }

    private float calcArea(float a)
    {
        return a * a * 0.433f;
    }

    public float getEdgeLen()
    {
        return edgeLen;
    }

    public void setEdgeLen(float edgeLen)
    {
        this.edgeLen = edgeLen;
        this.area = calcArea(edgeLen);
    }
}
