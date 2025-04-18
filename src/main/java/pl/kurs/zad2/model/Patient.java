package pl.kurs.zad2.model;

import java.util.Objects;

public class Patient extends Person {

    public Patient(int id, String lastName, String firstName, String birthDate, String pesel) {
        super(id, lastName, firstName, birthDate, pesel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return getId() == patient.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
