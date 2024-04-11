package ug.project.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ug.project.domain.Course;
import ug.project.domain.Student;
import ug.project.service.CourseService;

import java.util.List;

@RestController
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/api/courses")
    ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("api/courses/{name}")
    ResponseEntity<Course> getCourseByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(courseService.getCourseByName(name));
    }

    @GetMapping("api/courses/{name}/students")
    ResponseEntity<List<Student>> getStudentsByCourseName(@PathVariable("name") String name) {
        Course course = courseService.getCourseByName(name);
        return ResponseEntity.ok(courseService.getStudentsByCourse(course));
    }

    @GetMapping("api/courses/{name}/students-not-in-course")
    ResponseEntity<List<Student>> getStudentsNotInCourse(@PathVariable("name") String name) {
        Course course = courseService.getCourseByName(name);
        return ResponseEntity.ok(courseService.getStudentsNotInCourse(course));
    }

    @PostMapping("api/courses/{name}/student/{studentId}")
    ResponseEntity<Course> addStudentToCourse(@PathVariable("name") String name, @PathVariable("studentId") Long studentId) {
        Course course = courseService.getCourseByName(name);
        List<Student> courseStudents = courseService.getStudentsByCourse(course);

        if (courseStudents.stream().anyMatch(student -> student.getId() == studentId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(course);
        }

        return ResponseEntity.ok(courseService.addStudentToCourse(course, studentId));
    }

    @DeleteMapping("api/courses/{name}/student/{studentId}")
    public ResponseEntity<Course> removeStudentFromCourse(@PathVariable("name") String name, @PathVariable("studentId") Long studentId) {
        Course course = courseService.getCourseByName(name);

        return ResponseEntity.ok(courseService.removeStudentFromCourse(course, studentId));
    }
}
