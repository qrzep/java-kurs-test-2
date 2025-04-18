package pl.kurs.zad1.main;

import pl.kurs.utils.DateUtils;
import pl.kurs.zad1.service.BirthAnalyzer;

public class BirthAnalyzerRunner {
    public static void main(String[] args) {
        BirthAnalyzer ba = new BirthAnalyzer();
        ba.readSourceData();
        ba.printNameAndHeightOfHighestBoyAndGirl();
        int dayOfWeek = DateUtils.getDayOfTheWeek("1999-11-19");
        String dayOfWeekNameByNumber = DateUtils.getDayOfWeekByNumber(dayOfWeek);
        ba.printDayWithMostBirthsAndCountBirths();
        ba.printWomenNamesUnder25WhoGaveBirthToChildrenWeighingOver4000grams();
        ba.printNamesOfDaughtersWhoInheritedMothersName();
        ba.printMothersWhoGaveBirthToTwins();
    }
}
