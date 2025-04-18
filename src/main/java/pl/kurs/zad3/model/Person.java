package pl.kurs.zad3.model;

import pl.kurs.zad3.model.enums.Gender;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private String pesel;
    private String city;

    public Person(String firstName, String lastName, String pesel, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public String getCity() {
        return city;
    }

    public abstract double getIncome();

    public Gender getGender() {
        int numberDeterminingGender = Integer.parseInt(pesel.substring(9, 10));
        if (numberDeterminingGender % 2 == 0) {
            return Gender.FEMALE;
        } else {
            return Gender.MALE;
        }
    }

}
