package com.cs1530.coursereview.service;

import com.cs1530.coursereview.model.Teacher;
import com.cs1530.coursereview.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public Teacher createTeacher(String name) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, String name) {
        Teacher teacher = getTeacherById(id);
        teacher.setName(name);
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
