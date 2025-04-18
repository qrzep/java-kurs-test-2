package pl.kurs.zad1.service;

import pl.kurs.zad1.model.Child;
import pl.kurs.zad1.model.Mother;
import pl.kurs.zad1.model.ResultPair;
import pl.kurs.zad1.model.enums.Gender;
import pl.kurs.utils.DateUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BirthAnalyzer implements DataReader {
    private List<Child> children = new ArrayList<>();
    private Map<Integer, Mother> mothers = new HashMap<>();

    private void loadMothers() {
        try (BufferedReader br = new BufferedReader(new FileReader("zadania/mamy.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                Mother m = new Mother(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[2]));
                mothers.put(m.getId(), m);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadChildren() {
        try (BufferedReader br = new BufferedReader(new FileReader("zadania/noworodki.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                Mother mother = mothers.get(Integer.parseInt(values[6]));
                Child child = new Child(
                        Integer.parseInt(values[0]),
                        Gender.fromString(values[1]),
                        values[2],
                        values[3],
                        Integer.parseInt(values[4]),
                        Integer.parseInt(values[5]),
                        mother
                );
                children.add(child);
                if (mother != null) {
                    mother.addChild(child);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Mother> getMotherListFromMap() {
        return new ArrayList<>(mothers.values());
    }


    @Override
    public void readSourceData() {
        loadMothers();
        loadChildren();
    }

    private Mother getMotherById(int id) {
        for (Mother mother : getMotherListFromMap()) {
            if (mother.getId() == id) {
                return mother;
            }
        }
        return null;
    }

    private Child getHighestChildByGender(Gender gender) {
        Child highestChild = children.get(0);
        for (Child child : children) {
            if (child.getHeightInCm() > highestChild.getHeightInCm() && child.getGender().equals(gender)) {
                highestChild = child;
            }
        }
        return highestChild;
    }

    // zad. 1a
    public void printNameAndHeightOfHighestBoyAndGirl() {
        Child highestBoy = getHighestChildByGender(Gender.SON);
        Child highestGirl = getHighestChildByGender(Gender.DAUGHTER);
        System.out.println("Najwyższy chłopiec ma na imię " + highestBoy.getName() + " i ma " + highestBoy.getHeightInCm() + "cm wzrostu.");
        System.out.println("Najwyższa dziewczynka ma na imię " + highestGirl.getName() + " i ma " + highestGirl.getHeightInCm() + "cm wzrostu.");
    }

    private ResultPair<String, Integer> getDayWithHighestBirthCount() {
        int[] counts = new int[7];
        for (Child child : children) {
            counts[DateUtils.getDayOfTheWeek(child.getDate()) - 1]++;
        }

        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > max) {
                max = counts[i];
                maxIndex = i;
            }
        }
        return new ResultPair<>(DateUtils.getDayOfWeekByNumber(maxIndex), max);
    }

    //zad. 1b
    public void printDayWithMostBirthsAndCountBirths() {
        ResultPair<String, Integer> dayAndNumber = getDayWithHighestBirthCount();
        System.out.println("Najwięcej dzieci urodziło się w dniu: " + dayAndNumber.key() + " a było ich " + dayAndNumber.value());
    }

    private List<String> getWomenNamesUnder25WhoGaveBirthToChildrenWeighingOver4000Grams() {
        List<String> womenNames = new ArrayList<>();
        for (Mother mother : getMotherListFromMap()) {
            if (mother.getAge() < 25) {
                for (Child child : mother.getChildren()) {
                    if (child.getWeightInGrams() > 4000) {
                        womenNames.add(mother.getName());
                    }
                }
            }
        }
        return womenNames;
    }

    //zad 1c
    public void printWomenNamesUnder25WhoGaveBirthToChildrenWeighingOver4000grams() {
        List<String> womenNames = getWomenNamesUnder25WhoGaveBirthToChildrenWeighingOver4000Grams();
        System.out.println("Imiona kobiet, które urodziły poniżej 25 roku życia dzieci o wadze przekraczającej 4000 gram to: " + String.join(", ", womenNames));
    }

    private List<ResultPair<String, String>> getNamesOfDaughtersWhoInheritedMothersName() {
        List<ResultPair<String, String>> listOfDaughtersAndBirthDates = new ArrayList<>();

        for (Child child : children) {
            if (child.getName().equals(child.getMother().getName()) && child.getGender() == Gender.DAUGHTER) {
                listOfDaughtersAndBirthDates.add(new ResultPair<>(child.getName(), child.getDate()));
            }
        }
        return listOfDaughtersAndBirthDates;
    }

    //zad 1d
    public void printNamesOfDaughtersWhoInheritedMothersName() {
        List<ResultPair<String, String>> listOfDaughtersAndBirthDates = getNamesOfDaughtersWhoInheritedMothersName();
        System.out.println("Dziewczynki, które odziedziczyły imiona po matce to:");
        for (ResultPair<String, String> entry : listOfDaughtersAndBirthDates) {
            System.out.println(entry.key() + " urodzona " + entry.value());
        }
    }

    private List<Mother> getMothersWhoGaveBirthToTwins() {
        List<Mother> mothersWhoGaveBirthToTwins = new ArrayList<>();
        for (Mother mother : getMotherListFromMap()) {
            List<Child> mothersChildren = mother.getChildren();
            if (mothersChildren != null && mothersChildren.size() > 1) {
                for (int i = 0; i < mothersChildren.size(); i++) {
                    for (int j = i + 1; j < mothersChildren.size(); j++) {
                        if (mothersChildren.get(i).getDate().equals(mothersChildren.get(j).getDate())) {
                            mothersWhoGaveBirthToTwins.add(mother);
                        }
                    }
                }
            }
        }
        return mothersWhoGaveBirthToTwins;
    }

    //zad 1e
    public void printMothersWhoGaveBirthToTwins() {
        List<Mother> mothersList = getMothersWhoGaveBirthToTwins();
        System.out.println("Matki, które urodziły bliźnięta to: " + mothersList);
    }

}
