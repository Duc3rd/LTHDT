package com.example.btllthdt.service;

import com.example.btllthdt.dao.ProvinceDao;
import com.example.btllthdt.model.Province;
import java.util.List;

public class ProvinceService {
    private ProvinceDao provinceDao;

    public ProvinceService() {
        this.provinceDao = new ProvinceDao();
    }

    public List<Province> getAllProvinces() {
        return provinceDao.getAllProvinces();
    }
}
