package ug.project.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ug.project.domain.Course;
import ug.project.domain.Student;
import ug.project.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class StudentService {
    final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(long id) {
        return studentRepository.findById(id);
    }

    public List<Course> getStudentCourses(long id) {
        return studentRepository.findById(id).getCourses();
    }

    public Page<Student> searchStudents(String surname, LocalDate birthday, Integer index, Boolean enrolled, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return studentRepository.searchStudents(surname, birthday, index, enrolled, pageable);
    }
}
