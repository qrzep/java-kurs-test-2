package pl.kurs.zad2.model;

public abstract class Person {
    private int id;
    private String lastName;
    private String firstName;
    private String birthDate;
    private String pesel;

    public Person(int id, String lastName, String firstName, String pesel, String birthDate) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.pesel = pesel;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPesel() {
        return pesel;
    }

}
