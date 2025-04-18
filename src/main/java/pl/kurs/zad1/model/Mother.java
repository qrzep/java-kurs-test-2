package pl.kurs.zad1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mother {
    private int id;
    private String name;
    private int age;
    private List<Child> children;

    public Mother(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.children = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void addChild(Child child) {
        children.add(child);
    }

    public List<Child> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Mother{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mother mother = (Mother) o;
        return id == mother.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
