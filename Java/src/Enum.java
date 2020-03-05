public class Enum {


    public static void main(String[] args){
        Car camaro = Car.Camaro;

        if(camaro.equals(Car.Camaro))
            System.out.println("True");
        else if (camaro.equals(Car.Firebird))
            System.out.println("Rather not");
        else
            System.out.println("False");

        System.out.println(Car.Firebird);
    }

    public enum Car{
        Camaro,
        Firebird,
        Delorean,
        Falcon,
        Interceptor
    }
}
