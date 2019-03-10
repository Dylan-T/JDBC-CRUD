package nz.ac.vuw.swen301.assignment1;

import java.util.Objects;

/**
 * Represents a degree.
 * Note that id is immutable !!
 */
public class Degree {
    private String id;
    private String name;

    Degree(String id, String name) {
        this.id = id;
        this.name = name;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Degree degree = (Degree) o;
        return Objects.equals(id, degree.id) &&
                Objects.equals(name, degree.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
