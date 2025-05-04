package com.hospital.certification.model.service;

import com.hospital.certification.model.dao.CertificationDAO;
import com.hospital.certification.model.entity.Certification;

import java.util.List;

public class CertificationService {
    private final CertificationDAO dao = new CertificationDAO();

    public List<Certification> getAll()           { return dao.selectAll(); }
    public Certification getById(Long id)         { return dao.selectById(id); }
    public boolean create(Certification c)        { return dao.insert(c); }
    public boolean modify(Certification c)        { return dao.update(c); }
    public boolean remove(Long id)                { return dao.delete(id); }
}