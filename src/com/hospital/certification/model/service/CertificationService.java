package com.hospital.certification.model.service;

import com.hospital.certification.model.dao.CertificationDao;
import com.hospital.certification.model.entity.Certification;

import java.sql.SQLException;
import java.util.List;

public class CertificationService {
    private final CertificationDao dao = new CertificationDao();

    public List<Certification> getAll() {
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

    public Certification getById(long id) {
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

    public void create(Certification cert) {
        try {
            dao.connect();
            dao.insert(cert);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }

    public void modify(Certification cert) {
        try {
            dao.connect();
            dao.update(cert);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }

    public void remove(long id) {
        try {
            dao.connect();
            dao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }
}