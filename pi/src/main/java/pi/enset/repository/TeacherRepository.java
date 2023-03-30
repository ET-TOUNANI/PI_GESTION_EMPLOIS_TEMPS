package net.youssfi.coursesservice.repository;

import net.youssfi.coursesservice.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,String> {
    Teacher findByUserId(String userId);

}
