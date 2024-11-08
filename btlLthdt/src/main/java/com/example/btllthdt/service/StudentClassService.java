package com.example.btllthdt.service;

import com.example.btllthdt.dao.StudentClassDao;
import com.example.btllthdt.model.StudentClass;
import java.util.List;

public class StudentClassService {
    private StudentClassDao studentClassDao;

    public StudentClassService() {
        this.studentClassDao = new StudentClassDao();
    }

    public void registerForClass(StudentClass studentClass) {
        studentClassDao.registerForClass(studentClass);
    }

    public List<StudentClass> getAllStudentClass() {
        return studentClassDao.getAllStudentClass();
    }
}
