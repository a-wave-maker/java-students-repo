package ug.project.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ug.project.domain.Faculty;
import ug.project.domain.Student;
import ug.project.service.StudentService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/api/students")
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/api/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id) {
        Student foundStudent = studentService.getStudentById(id);

        if (foundStudent != null) {
            return ResponseEntity.ok(foundStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/students/search/results")
    public ResponseEntity<Page<Student>> searchStudents(
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

        return ResponseEntity.ok(studentsPage);
    }
}
