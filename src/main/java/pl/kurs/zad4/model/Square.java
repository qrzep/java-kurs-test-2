package pl.kurs.zad4.model;

import java.util.Objects;

public class Square extends Figure {
    private static final long serialVersionUID = 1L;
    private final int a;

    public Square(int a) {
        this.a = a;
    }

    protected Square(int id, int a)
    {
        super(id);
        this.a = a;
    }

    @Override
    protected String getDescription() {
        return "Kwadrat o boku " + a + ".";
    }

    @Override
    public double getArea() {
        return a * a;
    }

    @Override
    public double getPerimeter() {
        return 4 * a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return a == square.a;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a);
    }
}
