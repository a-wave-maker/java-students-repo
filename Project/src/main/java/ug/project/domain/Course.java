package ug.project.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;

@Entity
public class Course {
    private long id;
    private String name;
    private Teacher teacher;
    private Faculty faculty;
    private List<Student> students;

    public Course() {}

    public Course(String name, List<Student> students, Teacher teacher, Faculty faculty) {
        this.name = name;
        this.students = students;
        this.teacher = teacher;
        this.faculty = faculty;
    }

    public Course(String name, Faculty faculty, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
        this.faculty = faculty;
        this.students = Collections.<Student>emptyList();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @OneToOne(fetch = FetchType.EAGER)
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @ManyToOne
    @JsonBackReference
    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        Student foundStudent = null;

        for (Student s : students) {
            if (s.getId() == student.getId()) {
                foundStudent = s;
                break;
            }
        }

        foundStudent.removeCourse(this);

        this.students.remove(student);
    }
}
