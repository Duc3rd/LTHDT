package com.example.btllthdt.service;

import com.example.btllthdt.dao.CertificateDao;
import com.example.btllthdt.model.Certificate;
import java.util.List;

public class CertificateService {
    private CertificateDao certificateDao;

    public CertificateService() {
        this.certificateDao = new CertificateDao();
    }

    public void issueCertificate(Certificate certificate) {
        certificateDao.issueCertificate(certificate);
    }

    public List<Certificate> getAllCertificates() {
        return certificateDao.getAllCertificates();
    }
}
