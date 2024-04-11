package ug.project.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ug.project.domain.Faculty;
import ug.project.repository.FacultyRepository;

import java.util.List;

@Service
@Transactional
public class FacultyService {
    final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty getFacultyByName(String name) {
        return facultyRepository.findByName(name);
    }

    public Faculty addFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        Faculty faculty = facultyRepository.findById(id);

        facultyRepository.deleteById(id);
        return faculty;
    }
}
