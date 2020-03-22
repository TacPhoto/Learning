package GenericTypes;


interface Shape {
    String getName();
}

class Circle implements Shape {
    public String getName() {
        return "circle";
    }
}

class Rectangle implements Shape {
    public String getName() {
        return "rectangle";
    }
}


class Square extends Rectangle {
    public String getName() {
        return "square";
    }
}


class BoxOfShapes<T extends Shape> {
    private T element;

    public BoxOfShapes(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public String getElementName() {
        return element.getName();
    }
}


public class Main2 {

    public static void main(String[] args){

        BoxOfShapes<Circle> circleBox = new BoxOfShapes<>(new Circle());
        //BoxOfShapes<Apple> appleBox; // will not compile, cannot use Apple class here

        Rectangle rectangle = new Rectangle();
        Square    square    = new Square();
        Rectangle rectangleS = new Square();     // this one works
        //Square    squareR   = new Rectangle(); // cannot do so, this one does not

        System.out.println(rectangle.getName());
        System.out.println(square.getName());
        System.out.println(rectangleS.getName());

        }
}

