package com.hospital.leave.model.service;

import com.hospital.leave.model.dao.LeaveDao;
import com.hospital.leave.model.entity.Leave;

import java.sql.SQLException;
import java.util.List;

public class LeaveService {
    private final LeaveDao dao = new LeaveDao();

    public boolean applyLeave(Leave l) {
        dao.connect();
        boolean ok = dao.insert(l);
        dao.close();
        return ok;
    }

    public boolean approveLeave(Long id, Long approverId) {
        dao.connect();
        boolean ok = dao.updateStatus(id, "APPROVED", approverId);
        dao.close();
        return ok;
    }

    public boolean rejectLeave(Long id, Long approverId) {
        dao.connect();
        boolean ok = dao.updateStatus(id, "REJECTED", approverId);
        dao.close();
        return ok;
    }

    public List<Leave> getByEmployee(Long empId) {
        dao.connect();
        List<Leave> list = dao.findByEmployee(empId);
        dao.close();
        return list;
    }

    public boolean cancelLeave(Long id) {
        dao.connect();
        boolean ok = dao.delete(id);
        dao.close();
        return ok;
    }
}