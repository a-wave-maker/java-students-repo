package ug.project.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ug.project.domain.Course;
import ug.project.domain.Faculty;
import ug.project.domain.Student;
import ug.project.domain.Teacher;
import ug.project.repository.CourseRepository;
import ug.project.repository.StudentRepository;

import java.util.List;

@Service
@Transactional
public class CourseService {
    final CourseRepository courseRepository;
    final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getCoursesByFaculty(Faculty faculty) {
        return courseRepository.findByFaculty(faculty);
    }

    public Course getCourseByName(String name) {
        return courseRepository.findByName(name);
    }

    public List<Student> getStudentsByCourse(Course course) {
        return studentRepository.findByCourses(course);
    }

    public List<Student> getStudentsNotInCourse(Course course) {
        return studentRepository.findStudentsNotInCourse(course);
    }

    public Teacher getTeacherByCourse(Course course) {
        return course.getTeacher();
    }

    public Course addStudentToCourse(Course course, long studentId) {
        Student student = studentRepository.findById(studentId);
        course.addStudent(student);
        student.addCourse(course);
        return courseRepository.save(course);
    }

    public Course removeStudentFromCourse(Course course, long studentId) {
        Student student = studentRepository.findById(studentId);
        course.removeStudent(student);
        student.removeCourse(course);
        return courseRepository.save(course);
    }
}
