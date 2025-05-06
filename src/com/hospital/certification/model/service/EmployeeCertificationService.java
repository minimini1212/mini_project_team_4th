package com.hospital.certification.model.service;

import com.hospital.certification.model.dao.EmployeeCertificationDao;
import com.hospital.certification.model.entity.EmployeeCertification;

import java.sql.SQLException;
import java.util.List;

public class EmployeeCertificationService {
    private final EmployeeCertificationDao dao = new EmployeeCertificationDao();

    public List<EmployeeCertification> getAll() {
        try {
            dao.connect();
            return dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        } finally {
            dao.close();
        }
    }

    public List<EmployeeCertification> getByEmployee(long empId) {
        try {
            dao.connect();
            return dao.findByEmployeeId(empId);
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        } finally {
            dao.close();
        }
    }

    public EmployeeCertification getById(long id) {
        try {
            dao.connect();
            return dao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            dao.close();
        }
    }

    public void create(EmployeeCertification ec) {
        try {
            dao.connect();
            dao.insert(ec);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }

    public void modify(EmployeeCertification ec) {
        try {
            dao.connect();
            dao.update(ec);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close(); } } }