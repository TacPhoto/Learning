package AbstractClasses1;

public class Square extends Shape
{
    private float edgeLen;
    private float area;

    public Square(float edgeLen)
    {
        this.edgeLen = edgeLen;
        this.area = calcArea(edgeLen);
    }

    @Override
    public void showParams()
    {
        System.out.println("Type: Square  Area: " + area + "   Edge Length: " + edgeLen);
    }

    @Override
    public float getArea()
    {
        return area;
    }

    private float calcArea(float a)
    {
        return a * a;
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
