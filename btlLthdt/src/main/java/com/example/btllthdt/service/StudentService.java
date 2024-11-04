package com.example.btllthdt.service;

import com.example.btllthdt.dao.StudentDao;
import com.example.btllthdt.model.student;

import java.util.List;

public class StudentService {
    private StudentDao studentDao;

    public StudentService() {
        this.studentDao = new StudentDao();
    }

    public void addStudent(student student) {
        studentDao.insertStudent(student);
    }

    public List<student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public List<student> getStudentsByProvince(int provinceId) {
        return studentDao.getStudentsByProvince(provinceId);
    }

    public void updateStudent(student student) {
        studentDao.updateStudent(student);
    }

    public void deleteStudent(int studentId) {
        studentDao.deleteStudent(studentId);
    }
}
