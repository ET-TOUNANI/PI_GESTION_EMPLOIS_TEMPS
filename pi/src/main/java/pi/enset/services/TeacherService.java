package net.youssfi.coursesservice.services;

import net.youssfi.coursesservice.entities.Teacher;

public interface TeacherService {
    Teacher addNewTeacher(String firstName, String lastName, String userId);
}
