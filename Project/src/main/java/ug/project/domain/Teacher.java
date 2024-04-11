package ug.project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
public class Teacher {
    private long id;
    @NotEmpty(message = "Name cannot be empty.")
    private String name;
    @NotEmpty(message = "Surname cannot be empty.")
    private String surname;
    @Pattern(regexp = "dr|prof|mgr", message = "Invalid value. Must be dr, prof or mgr.")
    private String title;

    public Teacher() {}

    public Teacher(long id, String name, String surname, String title) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.title = title;
    }

    public Teacher(String name, String surname, String title) {
        this.name = name;
        this.surname = surname;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
