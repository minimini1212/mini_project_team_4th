package com.hospital.leave.model.service;

import com.hospital.leave.model.dao.LeaveUsageStatDao;
import com.hospital.leave.model.entity.LeaveUsageStat;

import java.util.List;

public class LeaveUsageStatService {
    private final LeaveUsageStatDao dao=new LeaveUsageStatDao();

    public boolean upsert(LeaveUsageStat s){ dao.connect(); boolean ok=dao.upsert(s); dao.close(); return ok; }
    public List<LeaveUsageStat> stats(Long dept,int yr,int mon){ dao.connect(); List<LeaveUsageStat> list=dao.findByDeptMonth(dept,yr,mon); dao.close(); return list; }
}