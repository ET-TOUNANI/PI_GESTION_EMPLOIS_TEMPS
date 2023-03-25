package net.youssfi.coursesservice.services;

import lombok.extern.slf4j.Slf4j;
import net.youssfi.coursesservice.entities.Teacher;
import net.youssfi.coursesservice.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    @Override
    public Teacher addNewTeacher(String firstName, String lastName, String userId){
        Teacher teacher=Teacher.builder()
                .id(UUID.randomUUID().toString())
                .firstName(firstName)
                .lastName(lastName)
                .userId(userId)
                .build();
        return teacherRepository.save(teacher);
    }
}
