package ug.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ug.project.domain.Course;
import ug.project.domain.Faculty;
import ug.project.domain.Student;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Faculty findByName(String name);

    Faculty findById(long id);
}
