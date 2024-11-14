package com.example.btllthdt.model;

public class StudentClass {
    private int studentId;
    private int classId;

    public StudentClass(int studentId, int classId) {
        this.studentId = studentId;
        this.classId = classId;
    }

    public StudentClass() {
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public int getClassId() {
        return classId;
    }
    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void add(StudentClass studentClass) {
    }
}
