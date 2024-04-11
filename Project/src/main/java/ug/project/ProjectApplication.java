package ug.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ug.project.domain.Course;
import ug.project.domain.Faculty;
import ug.project.domain.Student;
import ug.project.domain.Teacher;
import ug.project.repository.CourseRepository;
import ug.project.repository.FacultyRepository;
import ug.project.repository.StudentRepository;
import ug.project.repository.TeacherRepository;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner addExampleData(
            FacultyRepository facultyRepository,
            CourseRepository courseRepository,
            TeacherRepository teacherRepository,
            StudentRepository studentRepository) {
        return args -> {
            // Clear the existing data
            studentRepository.deleteAll();
            courseRepository.deleteAll();
            teacherRepository.deleteAll();
            facultyRepository.deleteAll();

            // Create faculties
            Faculty faculty1 = new Faculty("Faculty of Science");
            Faculty faculty2 = new Faculty("Faculty of Arts");
            facultyRepository.save(faculty1);
            facultyRepository.save(faculty2);

            Teacher teacher1 = new Teacher("John", "Doe", "dr");
            Teacher teacher2 = new Teacher("Jane", "Doe", "prof");
            Teacher teacher3 = new Teacher("Michael", "Jordan", "mgr");
            teacherRepository.save(teacher1);
            teacherRepository.save(teacher2);
            teacherRepository.save(teacher3);

            // Create courses and associate with faculties
            Course course1 = new Course("Biology", faculty1, teacher1);
            Course course2 = new Course("Chemistry", faculty1, teacher2);
            Course course3 = new Course("Literature", faculty2, teacher3);
            courseRepository.save(course1);
            courseRepository.save(course2);
            courseRepository.save(course3);

            // Create students and associate with courses
            for (int i = 1; i <= 10; i++) {
                Student student = new Student("Name" + i, "Surname" + i, "email" + i + "@gmail.com", LocalDate.of(2000+i, 1, 1), 100000 + i, true);
                if (i <= 2) {
                    student.setCourses(List.of(course1));
                } else if (i > 2 && i <= 5) {
                    student.setCourses(List.of(course1, course2));
                } else if (i > 5 && i <= 7) {
                    student.setCourses(List.of(course3));
                }
                // Students 8, 9, 10 will not be associated with any course
                studentRepository.save(student);
            }
        };
    }
}
