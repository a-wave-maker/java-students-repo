package ug.project.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Faculty {
    private long id;
    private String name;
    private List<Course> courses;

    public Faculty() {}

    public Faculty(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public Faculty(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) { this.courses = courses; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
