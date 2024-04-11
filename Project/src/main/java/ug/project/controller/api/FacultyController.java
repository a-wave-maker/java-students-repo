package ug.project.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ug.project.domain.Faculty;
import ug.project.service.FacultyService;

import java.util.List;

@Controller
public class FacultyController {

    final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/api/faculties")
    public ResponseEntity<List<Faculty>> getFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @GetMapping("/api/faculties/{name}")
    public ResponseEntity<Faculty> getFacultyByName(@PathVariable("name") String name) {
        Faculty foundFaculty = facultyService.getFacultyByName(name);

        if (foundFaculty != null) {
            return ResponseEntity.ok(foundFaculty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/faculties")
    public ResponseEntity<Faculty> createFaculty(Faculty faculty) {
        return new ResponseEntity<>(facultyService.addFaculty(faculty), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/faculties/{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable("id") Long id) {
        return new ResponseEntity<>(facultyService.deleteFaculty(id), HttpStatus.NO_CONTENT);
    }
}
