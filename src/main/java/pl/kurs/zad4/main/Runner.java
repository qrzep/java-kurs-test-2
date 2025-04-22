package pl.kurs.zad4.main;

import pl.kurs.zad4.model.Figure;
import pl.kurs.zad4.model.Square;
import pl.kurs.zad4.service.FigureAnalyzer;

import java.util.Arrays;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        List<Figure> figury = Arrays.asList(Figure.createSquare(10), Figure.createCircle(20), Figure.createRectangle(10,20));

        for(Figure f : figury) {
            System.out.println(f);
        }

        System.out.println(figury.contains(new Square(10)));
        Figure najwiekszyObwod = FigureAnalyzer.getFigureWithBiggestPerimeter(figury);
        Figure najwiekszePole = FigureAnalyzer.getFigureWithBiggestArea(figury);

        FigureAnalyzer.writeFigureListToFile(figury, "zadania/figury.dupa");
        List<Figure> figuryPlik = FigureAnalyzer.readFigureListFromFile("zadania/figury.dupa");
        Square square = new Square(10);
        Figure square1 = new Square(10);

    }
}
