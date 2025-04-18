package pl.kurs.zad1.model;

import pl.kurs.zad1.model.enums.Gender;

import java.util.Objects;

public class Child {
    private int id;
    private Gender gender;
    private String name;
    private String date;
    private int weightInGrams;
    private int heightInCm;
    private Mother mother;

    public Child(int id, Gender gender, String name, String date, int weightInGrams, int heightInCm, Mother mother) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.date = date;
        this.weightInGrams = weightInGrams;
        this.heightInCm = heightInCm;
        this.mother = mother;
    }

    public int getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getWeightInGrams() {
        return weightInGrams;
    }

    public int getHeightInCm() {
        return heightInCm;
    }

    public Mother getMother() {
        return mother;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", gender=" + gender +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", weightInGrams=" + weightInGrams +
                ", heightInCm=" + heightInCm +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return id == child.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
