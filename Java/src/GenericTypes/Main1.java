package GenericTypes;

class Orange {}


class Apple{}


class Box<T>{
    public T fruit;

    public Box(T fruit){
        this.fruit = fruit;
    }

    public T getFruit(){
        return fruit;
    }

}


class Pair<T, S> {
    private T first;
    private S second;

    public Pair(T first, S second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}


public class Main1 {

    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<Apple>(new Apple());
        Box<Orange> orangeBox = new Box<Orange>(new Orange());

        Orange fruit = orangeBox.getFruit();

        Pair<Box<Orange>, Box<Apple>> pairOfBoxes =
                new Pair<>(
                        new Box<>(new Orange()),
                        new Box<>(new Apple())
                );

        System.out.println(pairOfBoxes.getFirst());

    }
}
