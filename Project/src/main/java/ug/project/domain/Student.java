package ug.project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@Entity
public class Student {
    private long id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Surname cannot be empty")
    @Size(min = 2, message = "Surname must be at least 2 letters long")
    private String surname;
    @Email(message = "Invalid email format")
    private String email;
    @Past(message = "Birthday must be in the past")
    private LocalDate birthday;
    @Digits(integer = 6, fraction = 0, message = "Index must be a 6-digit number")
    private int index;
    @NotNull(message = "Enrolled needs a value")
    private boolean enrolled;
    private List<Course> courses;

    @ManyToMany(fetch = FetchType.EAGER)
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Student(long id, String name, String surname, String email, LocalDate birthday, int index, boolean enrolled) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
        this.index = index;
        this.enrolled = enrolled;
    }

    public Student(String name, String surname, String email, LocalDate birthday, int index, boolean enrolled) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
        this.index = index;
        this.enrolled = enrolled;
    }

    public Student() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isEnrolled() {
        return enrolled;
    }

    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
    }

    public int calculateCourseCount() {
        return courses.size();
    }

    public boolean isEnrolled(Course course) {
        return courses.contains(course);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        System.out.println("Removing course with id: " + course.getId());
        for (Course course1 : courses) {
            System.out.println(course1.getId());
        }
        courses.removeIf(course1 -> course1.getId() == course.getId());
        System.out.println("Removing course with id: " + course.getId());
        for (Course course1 : courses) {
            System.out.println(course1.getId());
        }
    }
}
