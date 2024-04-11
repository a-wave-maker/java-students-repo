package ug.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ug.project.domain.Course;
import ug.project.domain.Student;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCourses(Course course);

    Student findById(long id);

    @Query("SELECT s FROM Student s WHERE :course NOT MEMBER OF s.courses")
    List<Student> findStudentsNotInCourse(@Param("course") Course course);

    @Query("SELECT c, COUNT(s) FROM Course c JOIN c.students s GROUP BY c")
    List<Object[]> countStudentsInEachCourse();
    @Query("SELECT s FROM Student s " +
            "WHERE (:surname IS NULL OR s.surname LIKE %:surname%) " +
            "AND (:birthday IS NULL OR s.birthday = :birthday) " +
            "AND (:index IS NULL OR s.index = :index) " +
            "AND (:enrolled IS NULL OR s.enrolled = :enrolled)")
    Page<Student> searchStudents(
            @Param("surname") String surname,
            @Param("birthday") LocalDate birthday,
            @Param("index") Integer index,
            @Param("enrolled") Boolean enrolled,
            Pageable pageable);
}
