package Exercise1;

public class Tree {
    Fruit[] fruits = new Fruit[100];
    int pears = 0;
    int oranges = 0;
    int apples = 0;

    private static int chooseFruit() {
        float min = 0.f;
        float max = 2.99f;
        return (int) (min + Math.random() * (max - min));
    }

    public void harvestSingle(int count)
    {
        switch (chooseFruit())
        {
            case 0:
                fruits[count] = new Pear();
                this.pears++;
                break;
            case 1:
                fruits[count] = new Orange();
                this.oranges++;
                break;
            case 2:
                fruits[count] = new Apple();
                this.apples++;
                break;
        }
    }

    public boolean isHeavy()
    {
        double weight = 0;

        for(Fruit fruit : fruits)
        {
            if (fruit == null)
                break;
            weight += fruit.getMass();
        }

        return weight / 1000. >= 5.;
    }

    public void harvesting()
    {
        int count = 0;

        while(!isHeavy())
        {
            harvestSingle(count);
            count++;

            if(count == fruits.length) {
                if(!isHeavy())
                    System.out.println("Could not harvest 5kg");
                break;
            }
        }

        System.out.println("End of harvesting");
        System.out.println("Apples: " + this.apples + " Pears: " + this.pears + " Oranges: " + this.oranges);
    }
}
