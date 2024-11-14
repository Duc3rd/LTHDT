package com.example.btllthdt.model;

public class Certificate {
    private int id;
    private int studentId;
    private int classId;
    private String status;

    public Certificate(int id, int studentId, int classId, String status) {
        this.id = id;
        this.studentId = studentId;
        this.classId = classId;
        this.status = status;
    }

    public Certificate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
