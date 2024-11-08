package com.example.btllthdt.service;

import com.example.btllthdt.dao.StudentDao;
import com.example.btllthdt.model.Student;

import java.util.List;

public class StudentService {
    private StudentDao studentDao;

    public StudentService() {
        this.studentDao = new StudentDao();
    }

    public void addStudent(Student student) {
        studentDao.insertStudent(student);
    }

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public List<Student> getStudentsByProvince(int provinceId) {
        return studentDao.getStudentsByProvince(provinceId);
    }

    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    public void deleteStudent(int studentId) {
        studentDao.deleteStudent(studentId);
    }
}
