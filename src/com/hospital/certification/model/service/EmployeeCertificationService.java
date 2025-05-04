package com.hospital.certification.model.service;

import com.hospital.certification.model.dao.EmployeeCertificationDAO;
import com.hospital.certification.model.entity.EmployeeCertification;

import java.util.List;

public class EmployeeCertificationService {
    private final EmployeeCertificationDAO dao = new EmployeeCertificationDAO();

    public boolean addCertification(EmployeeCertification ec) {
        // alert_date 자동 계산 (expiryDate.minusDays(30)) 해준 뒤
        return dao.insert(ec);
    }
    public List<EmployeeCertification> getByEmployee(Long empId) {
        return dao.selectByEmployee(empId);
    }
    public boolean removeCertification(Long empCertId) {
        return dao.delete(empCertId);
    }
}