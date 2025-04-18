package pl.kurs.zad2.model;

import java.util.Objects;

public class Doctor extends Person {
    private String specialization;
    private String nip;

    public Doctor(int id, String lastName, String firstName, String specialization, String birthDate, String nip, String pesel) {
        super(id, lastName, firstName, pesel, birthDate);
        this.specialization = specialization;
        this.nip = nip;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getNip() {
        return nip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return getId() == doctor.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
