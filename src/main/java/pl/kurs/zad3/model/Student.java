package pl.kurs.zad3.model;

public class Student extends Person {
    private static final long serialVersionUID = 1L;
    private String group;
    private double scholarship;

    public Student(String firstName, String lastName, String pesel, String city, String group, double scholarship) {
        super(firstName, lastName, pesel, city);
        this.group = group;
        this.scholarship = scholarship;
    }

    @Override
    public double getIncome() {
        return scholarship;
    }
}
