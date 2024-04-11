package ug.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ug.project.domain.Course;
import ug.project.domain.Faculty;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByFaculty(Faculty faculty);
    Course findByName(String name);

}
