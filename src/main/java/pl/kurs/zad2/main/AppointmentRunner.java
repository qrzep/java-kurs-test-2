package pl.kurs.zad2.main;

import pl.kurs.zad2.model.Doctor;
import pl.kurs.zad2.model.Patient;
import pl.kurs.zad2.service.AppointmentAnalyzer;

import java.util.List;

public class AppointmentRunner {
    public static void main(String[] args) {
        AppointmentAnalyzer aa = new AppointmentAnalyzer();
        aa.readSourceData();
        String specka = aa.getMostPopularSpecialization();
        Doctor najlepszyDoktor = aa.getDoctorWithHighestNumberOfVisits();
        Patient najlepszyPacjent = aa.getPatientWithHighestNumberOfVisits();
        int najwiecejWizytRok = aa.getYearWithTheHighestNumberOfVisits();
        List<Doctor> pieciuStaruchow = aa.get5OldestDoctors();
        List<Doctor> pieciuPracusi = aa.get5WhichHaveHighestNumberOfVisits();
        List<Patient> hipochondrycy = aa.getPatientsWhoHadAppointmentInAtLeast5Doctors();

    }
}
