package ug.project.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ug.project.service.FacultyService;

@Controller
public class WebFacultyController {
    final FacultyService facultyService;

    public WebFacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/faculties")
    public String faculties(Model model) {
        model.addAttribute("faculties", facultyService.getAllFaculties());
        return "faculties";
    }

    @GetMapping("/faculty/{id}/delete")
    public String deleteFaculty(@PathVariable("id") Long id) {
       facultyService.deleteFaculty(id);
       return "redirect:/faculties";
    }
}
