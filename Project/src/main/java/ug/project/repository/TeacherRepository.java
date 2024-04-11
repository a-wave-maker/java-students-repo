package ug.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ug.project.domain.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
