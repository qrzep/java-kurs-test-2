package pl.kurs.zad4.model;

import java.util.Objects;

public class Circle extends Figure {
    private static final long serialVersionUID = 1L;
    private final double r;

    public Circle(double r){
        super();
        this.r = r;
    }

    @Override
    protected String getDescription() {
        return "Koło o promieniu " + r + ".";
    }

    @Override
    public double getArea() {
        return Math.PI * r * r;
    }

    public double getPerimeter() {
        return 2 * Math.PI * r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.r, r) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r);
    }
}
