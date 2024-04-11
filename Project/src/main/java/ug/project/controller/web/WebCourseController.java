package ug.project.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ug.project.domain.Course;
import ug.project.repository.StudentRepository;
import ug.project.service.CourseService;
import ug.project.domain.Student;

import java.util.List;

@Controller
public class WebCourseController {
    final CourseService courseService;
    final StudentRepository studentRepository;

    public WebCourseController(CourseService courseService, StudentRepository studentRepository) {
        this.courseService = courseService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        List<Object[]> studentCount = studentRepository.countStudentsInEachCourse();
        model.addAttribute("courses", courses);
        model.addAttribute("studentCount", studentCount);
        return "courses";
    }

    @GetMapping("/courses/{name}/students")
    public String courseStudents(@PathVariable String name, Model model) {
        Course course = courseService.getCourseByName(name);
        List<Student> students = courseService.getStudentsByCourse(course);
        model.addAttribute("course", course);
        model.addAttribute("students", students);
        return "course-students";
    }

    @GetMapping("/courses/{name}/students-not-in-course")
    public String studentsNotInCourse(@PathVariable String name, Model model) {
        Course course = courseService.getCourseByName(name);
        List<Student> students = courseService.getStudentsNotInCourse(course);
        model.addAttribute("course", course);
        model.addAttribute("students", students);
        return "students-not-in-course";
    }
}
