package pl.kurs.zad4.model;

import java.io.Serializable;
import java.util.function.Supplier;

public abstract class Figure implements Serializable {
    private static final long serialVersionUID = 1L;
    private int instanceCounter;
    private static int globalCounter = 0;

    protected Figure() {
        this.instanceCounter = 0;
    }

    public static Figure createSquare(int a) {
        Figure square = new Square(a);
        square.incrementGlobalCounter();
        return square;
    }

    public static Figure createCircle(double r) {
        Figure circle = new Circle(r);
        circle.incrementGlobalCounter();
        return circle;
    }

    public static Figure createRectangle(int a, int b) {
        Figure rectangle = new Rectangle(a, b);
        rectangle.incrementGlobalCounter();
        return rectangle;
    }
    private void incrementGlobalCounter() {
        globalCounter++;
        this.instanceCounter = globalCounter;

    }

    private int getInstanceCounter() {
        return this.instanceCounter;
    }

    @Override
    public String toString() {
        return "Figura nr " + getInstanceCounter() + ": " + getDescription();
    }

    protected abstract String getDescription();

    public abstract double getArea();
    public abstract double getPerimeter();
}
