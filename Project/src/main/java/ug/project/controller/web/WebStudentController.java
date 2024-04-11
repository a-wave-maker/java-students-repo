package ug.project.controller.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ug.project.domain.Course;
import ug.project.domain.Student;
import ug.project.service.CourseService;
import ug.project.service.StudentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WebStudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    public WebStudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/{id}")
    public String student(@PathVariable Long id, Model model) {
        List<Course> courses = courseService.getAllCourses();
        Student student = studentService.getStudentById(id);

        List<Boolean> enrolledStatusList = new ArrayList<>();
        for (Course course : courses) {
            enrolledStatusList.add(student.isEnrolled(course));
        }

        model.addAttribute("courseList", courses);
        model.addAttribute("enrolledStatusList", enrolledStatusList);
        model.addAttribute("student", student);
        return "student";
    }

    @GetMapping("/students/search")
    public String searchStudents() {
        return "search";
    }

    @GetMapping("/students/search/results")
    public String searchStudents(
            Model model,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) LocalDate birthday,
            @RequestParam(required = false) Integer index,
            @RequestParam(required = false) Boolean enrolled,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<Student> studentsPage = studentService.searchStudents(surname, birthday, index, enrolled, page, size);

        model.addAttribute("students", studentsPage.getContent());
        model.addAttribute("currentPage", studentsPage.getNumber());
        model.addAttribute("totalPages", studentsPage.getTotalPages());
        model.addAttribute("totalItems", studentsPage.getTotalElements());

        return "search-results";
    }
}
