package nz.ac.vuw.swen301.assignment1;

import java.util.Objects;

/**
 * Represents a student.
 * Note that id is immutable !!
 */
public class Student {
    private String id = null;
    private String name = null;
    private String firstName = null;
    private Degree degree = null;

    Student(String id, String name, String firstName, Degree degree) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.degree = degree;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(firstName, student.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, firstName);
    }
}
