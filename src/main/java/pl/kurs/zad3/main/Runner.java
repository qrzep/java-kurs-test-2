package pl.kurs.zad3.main;

import pl.kurs.zad3.model.Employee;
import pl.kurs.zad3.model.Person;
import pl.kurs.zad3.model.Student;
import pl.kurs.zad3.service.PersonAnalyzer;

public class Runner {
    public static void main(String[] args) {

        Employee employee1 = new Employee("John", "Doe", "52010112345", "New York", "Professor", 5000.00);
        Employee employee2 = new Employee("Jane", "Smith", "75091298765", "Los Angeles", "Assistant Professor", 4000.00);
        Student student1 = new Student("Alice", "Brown", "99050343567", "Chicago", "Group A", 1500.00);
        Student student2 = new Student("Bob", "White", "96082724321", "San Francisco", "Group B", 1200.00);

        // Create an array of Person (Employee and Student)
        Person[] people = new Person[4];
        people[0] = employee1;
        people[1] = employee2;
        people[2] = student1;
        people[3] = student2;


        Person highestIncome = PersonAnalyzer.getPersonWithHighestIncome(people);
        int countWomen = PersonAnalyzer.getNumberOfWomenInPersonArray(people);
        PersonAnalyzer.writePersonArrayToFile(people, "zadania/person.pipa");
        Person[] wczytane = PersonAnalyzer.readPersonArrayFromFile("zadania/person.pipa");
    }
}
