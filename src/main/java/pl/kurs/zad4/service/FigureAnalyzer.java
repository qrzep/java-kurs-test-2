package pl.kurs.zad4.service;

import pl.kurs.zad4.model.Figure;

import java.io.*;
import java.util.List;

public class FigureAnalyzer {
    public static Figure getFigureWithBiggestPerimeter(List<Figure> listFigure) {
        double biggestPerimeter = 0;
        Figure figureWithBiggestPerimeter = null;
        for (Figure f : listFigure) {
            if (f.getPerimeter() > biggestPerimeter) {
                figureWithBiggestPerimeter = f;
                biggestPerimeter = f.getPerimeter();
            }
        }
        return figureWithBiggestPerimeter;
    }

    ;

    public static Figure getFigureWithBiggestArea(List<Figure> listFigure) {
        double biggestArea = 0;
        Figure figureWithBiggestArea = null;
        for (Figure f : listFigure) {
            if (f.getArea() > biggestArea) {
                figureWithBiggestArea = f;
                biggestArea = f.getPerimeter();
            }
        }
        return figureWithBiggestArea;
    }

    public static void writeFigureListToFile(List<Figure> listFigure, String filepath) {
        try (
                FileOutputStream fos = new FileOutputStream(filepath);
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(listFigure);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Figure> readFigureListFromFile(String filepath) {
        try (
                FileInputStream fis = new FileInputStream(filepath);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return (List<Figure>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erron on file load: " + filepath, e);
        }
    }
}
