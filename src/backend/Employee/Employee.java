package backend.Employee;

public class Employee {
    private String surname;
    private String name;
    private Position position;
    private int seniority;
    private double salary;

    public Employee(String surname, String name, Position position, int seniority, double salary) {
        this.surname = surname;
        this.name = name;
        this.position = position;
        this.seniority = seniority;
        this.salary = salary;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
