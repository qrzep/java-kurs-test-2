package pl.kurs.zad3.model;

public class Employee extends Person {
    private static final long serialVersionUID = 1L;
    private String position;
    private double salary;

    public Employee(String firstName, String lastName, String pesel, String city, String position, double salary) {
        super(firstName, lastName, pesel, city);
        this.position = position;
        this.salary = salary;
    }

    @Override
    public double getIncome() {
        return salary;
    }
}
