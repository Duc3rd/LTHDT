package com.example.btllthdt.service;

import com.example.btllthdt.dao.ClassDao;
import com.example.btllthdt.model.Class;

import java.util.List;

public class ClassService {
    private ClassDao classDao;

    public ClassService() {
        this.classDao = new ClassDao();
    }

    public void addClass(Class course) {
        classDao.insertClass(course);
    }

    // Thêm các phương thức khác tương tự như trong StudentService
}
