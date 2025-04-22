package pl.kurs.zad4.model;

import java.util.Objects;

public class Rectangle extends Figure {
    private static final long serialVersionUID = 1L;
    private final int a, b;

    public Rectangle(int a, int b) {
        this.a = a;
        this.b = b;
    }

    protected Rectangle(int id, int a, int b) {
        super(id);
        this.a = a;
        this.b = b;
    }

    @Override
    protected String getDescription() {
        return "Prostokąt o bokach " + a + "x" + b + ".";
    }

    @Override
    public double getArea() {
        return a * b;
    }

    @Override
    public double getPerimeter() {
        return 2 * a + 2 * b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return a == rectangle.a && b == rectangle.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
