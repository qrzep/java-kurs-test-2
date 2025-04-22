package pl.kurs.zad2.service;

import pl.kurs.utils.DateUtils;
import pl.kurs.zad2.model.Appointment;
import pl.kurs.zad2.model.Patient;
import pl.kurs.zad2.model.Doctor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class AppointmentAnalyzer implements DataReader {

    private HashMap<Integer, Doctor> doctors = new HashMap<>();
    private HashMap<Integer, Patient> patients = new HashMap<>();
    private List<Appointment> appointments = new ArrayList<>();

    private void loadDoctors() {
        try (BufferedReader br = new BufferedReader(new FileReader("zadania/lekarze.txt"))) {
            String headers = br.readLine();
            String line;
            List<String> seenNip = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                if (seenNip.contains(values[5])) {
                    continue;
                } else {
                    Doctor doctor = new Doctor(
                            Integer.parseInt(values[0]),
                            values[1],
                            values[2],
                            values[3],
                            values[4],
                            values[5],
                            values[6]
                    );
                    doctors.put(doctor.getId(), doctor);
                    seenNip.add(values[5]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPatients() {
        try (BufferedReader br = new BufferedReader(new FileReader("zadania/pacjenci.txt"))) {
            String headers = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                Patient patient = new Patient(
                        Integer.parseInt(values[0]),
                        values[1],
                        values[2],
                        values[3],
                        values[4]
                );
                patients.put(patient.getId(), patient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAppointments() {
        try (BufferedReader br = new BufferedReader(new FileReader("zadania/wizyty.txt"))) {
            String headers = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                if (doctors.containsKey(Integer.parseInt(values[0])) && patients.containsKey(Integer.parseInt(values[1]))) {
                    Doctor doctorToAdd = doctors.get(Integer.parseInt(values[0]));
                    Patient patientToAdd = patients.get(Integer.parseInt(values[1]));
                    Appointment appointment = new Appointment(
                            doctorToAdd,
                            patientToAdd,
                            values[2]);
                    appointments.add(appointment);
                    doctorToAdd.addAppointment(appointment);
                    patientToAdd.addAppointment(appointment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readSourceData() {
        loadDoctors();
        loadPatients();
        loadAppointments();
    }

    //2a
    public Doctor getDoctorWithHighestNumberOfVisits() {
        Map<Doctor, Integer> doctorsAppointmentCount = new HashMap<>();
        for (Appointment a : appointments) {
            doctorsAppointmentCount.merge(a.getDoctor(), 1, Integer::sum);
        }

        Doctor doctorWithHighestNumberOfVisits = null;
        int maxVisitsTracker = 0;

        for (Map.Entry<Doctor, Integer> entry : doctorsAppointmentCount.entrySet()) {
            if (doctorWithHighestNumberOfVisits == null || entry.getValue() > maxVisitsTracker) {
                doctorWithHighestNumberOfVisits = entry.getKey();
                maxVisitsTracker = entry.getValue();
            }
        }
        return doctorWithHighestNumberOfVisits;
    }

    //2b
    public Patient getPatientWithHighestNumberOfVisits() {
        Map<Patient, Integer> patientsAppointmentCount = new HashMap<>();
        for (Appointment a : appointments) {
            patientsAppointmentCount.merge(a.getPatient(), 1, Integer::sum);
        }

        Patient patientWithHighestNumberOfVisits = null;
        int maxVisitsTracker = 0;

        for (Map.Entry<Patient, Integer> entry : patientsAppointmentCount.entrySet()) {
            if (patientWithHighestNumberOfVisits == null || entry.getValue() > maxVisitsTracker) {
                patientWithHighestNumberOfVisits = entry.getKey();
                maxVisitsTracker = entry.getValue();
            }
        }
        return patientWithHighestNumberOfVisits;
    }

    //2c
    public String getMostPopularSpecialization() {
        Map<String, Integer> specializationCounter = new HashMap<>();
        for (Appointment a : appointments) {
            specializationCounter.merge(a.getDoctor().getSpecialization(), 1, Integer::sum);
        }

        String mostPopularSpecialization = null;
        int tracker = 0;

        for (Map.Entry<String, Integer> entry : specializationCounter.entrySet()) {
            if (mostPopularSpecialization == null || entry.getValue() > tracker) {
                mostPopularSpecialization = entry.getKey();
                tracker = entry.getValue();
            }
        }
        return mostPopularSpecialization;
    }

    public int getYearWithTheHighestNumberOfVisits() {
        Map<Integer, Integer> visitsByYearCounter = new HashMap<>();
        for (Appointment a : appointments) {
            visitsByYearCounter.merge(DateUtils.getYearFromDateInString(a.getDate()), 1, Integer::sum);
        }

        int yearWithTheHighestNumberOfVisits = 0;
        int tracker = 0;

        for (Map.Entry<Integer, Integer> entry : visitsByYearCounter.entrySet()) {
            if (yearWithTheHighestNumberOfVisits == 0 || entry.getValue() > tracker) {
                yearWithTheHighestNumberOfVisits = entry.getKey();
                tracker = entry.getValue();
            }
        }
        return yearWithTheHighestNumberOfVisits;
    }

    public List<Doctor> getDoctorsListFromMap() {
        return new ArrayList<>(doctors.values());
    }

    public List<Doctor> get5OldestDoctors() {
        Map<Doctor, LocalDate> doctorsAndBirthDates = new HashMap<>();
        for (Doctor doc : getDoctorsListFromMap()) {
            doctorsAndBirthDates.put(doc, DateUtils.getDateFromString(doc.getBirthDate()));
        }
        List<Doctor> fiveOldest = doctorsAndBirthDates.entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getValue()))
                .map(Map.Entry::getKey)
                .limit(5)
                .toList();
        return fiveOldest;
    }

    public List<Doctor> get5WhichHaveHighestNumberOfVisits() {
        Map<Doctor, Integer> doctorsAndVisits = new HashMap<>();
        for (Appointment a : appointments) {
            doctorsAndVisits.merge(a.getDoctor(), 1, Integer::sum);
        }

        List<Doctor> fiveWithHighestNumberOfVisits = doctorsAndVisits.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .map(Map.Entry::getKey)
                .limit(5)
                .toList();
        return fiveWithHighestNumberOfVisits;
    }

    public List<Patient> getPatientsWhoHadAppointmentInAtLeast5Doctors() {
        Map<Patient, Set<Doctor>> patientAndDoctors = new HashMap<>();

        for (Appointment a : appointments) {
            patientAndDoctors.computeIfAbsent(a.getPatient(), docs -> new HashSet<>())
                    .add(a.getDoctor());
        }

        List<Patient> patientsList = new ArrayList<>();
        for (Map.Entry<Patient, Set<Doctor>> entry : patientAndDoctors.entrySet()) {
            if (entry.getValue().size() >= 5) {
                patientsList.add(entry.getKey());
            }
        }
        return patientsList;
    }

    public List<Doctor> getDoctorsWithOneUniquePatient() {
        Map<Doctor, Set<Patient>> doctorPatientsMap = new HashMap<>();

        for (Appointment a : appointments) {
            doctorPatientsMap.computeIfAbsent(a.getDoctor(), p -> new HashSet<>()).add(a.getPatient());
        }

        List<Doctor> doctorsWithUniquePatient = new ArrayList<>();
        for (Map.Entry<Doctor, Set<Patient>> entry : doctorPatientsMap.entrySet()) {
            if (entry.getValue().size() == 1) {
                doctorsWithUniquePatient.add(entry.getKey());
            }
        }
        return doctorsWithUniquePatient;
    }

}
