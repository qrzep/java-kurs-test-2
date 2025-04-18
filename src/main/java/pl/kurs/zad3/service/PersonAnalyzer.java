package pl.kurs.zad3.service;

import pl.kurs.zad3.model.Person;
import pl.kurs.zad3.model.enums.Gender;

import java.io.*;

public class PersonAnalyzer {
    public static Person getPersonWithHighestIncome(Person[] arrPerson) {
        Person person = null;
        double highestSalary = 0;
        for (Person p : arrPerson) {
            if (person == null || person.getIncome() > highestSalary) {
                person = p;
                highestSalary = p.getIncome();
            }
        }
        return person;
    }

    public static int getNumberOfWomenInPersonArray(Person[] arrPerson) {
        int womenCounter = 0;

        for (Person p : arrPerson) {
            if (p.getGender() == Gender.FEMALE) {
                womenCounter++;
            }
        }
        return womenCounter;
    }

    public static void writePersonArrayToFile(Person[] arrPerson, String filepath) {
        try (
                FileOutputStream fos = new FileOutputStream(filepath);
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(arrPerson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Person[] readPersonArrayFromFile(String filepath) {
        try (
                FileInputStream fis = new FileInputStream(filepath);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return (Person[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erron on file load: " + filepath, e);
        }
    }
}
