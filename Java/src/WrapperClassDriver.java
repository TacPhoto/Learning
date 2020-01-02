class Something
{
    int model, no;

    Something(int model, int no)
    {
        this.model = model;
        this.no = no;
    }

    void print()
    {
        System.out.println("model: " + model + "  no: " + no);
    }
}


class WrapperClass
{
    Something o;

    WrapperClass(Something o)   {this.o = o;}
}


public class WrapperClassDriver {
    public static void swap(WrapperClass ow1, WrapperClass ow2) {
        Something temp = ow1.o;
        ow1.o = ow2.o;
        ow2.o = temp;
    }

    public static void main(String[] args) {
        Something o1 = new Something(1, 2);
        Something o2 = new Something(3, 4);
        o1.print();
        o2.print();
        line();
        WrapperClass ow1 = new WrapperClass(o1);
        WrapperClass ow2 = new WrapperClass(o2);
        swap(ow1, ow2);
        ow1.o.print();
        ow2.o.print();
        line();
        o1.print();
        o2.print();
    }


    public static void line(){System.out.println("---------------------------");
    }

}