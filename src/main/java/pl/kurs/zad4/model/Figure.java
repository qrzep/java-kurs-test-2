package pl.kurs.zad4.model;

import java.io.Serializable;

public abstract class Figure implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int counter = 0;
    private int id = 0;

    protected Figure() {
    }

    protected Figure(int id) {
        this.id = id;
    }

    public static Figure createSquare(int a) {
        return new Square(++counter, a);
    }

    public static Figure createCircle(double r) {
        return new Circle(++counter, r);
    }

    public static Figure createRectangle(int a, int b) {
        return new Rectangle(++counter, a, b);
    }

    @Override
    public String toString() {
        return "Figura nr " + id + ": " + getDescription();
    }

    protected abstract String getDescription();

    public abstract double getArea();
    public abstract double getPerimeter();
}
