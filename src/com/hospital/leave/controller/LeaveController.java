package com.hospital.leave.controller;

import com.hospital.leave.model.entity.Leave;
import com.hospital.leave.model.entity.LeaveUsageStat;
import com.hospital.leave.model.service.LeaveService;
import com.hospital.leave.model.service.LeaveUsageStatService;
import com.hospital.leave.view.LeaveView;
import com.hospital.leave.view.LeaveUsageStatView;

import java.util.List;

public class LeaveController {
    private final LeaveService service = new LeaveService();
    private final LeaveView    view    = new LeaveView();
    private final LeaveUsageStatService statService = new LeaveUsageStatService();
    private final LeaveUsageStatView    statView    = new LeaveUsageStatView();

    public void leaveMenu() {
        while (true) {
            view.showMenu();
            int menu = view.inputMenu();
            switch (menu) {
                case 1:
                    applyLeave();
                    break;
                case 2:
                    approveReject();
                    break;
                case 3:
                    history();
                    break;
                case 4:
                    cancel();
                    break;
                case 0:
                    return;
                default:
                    view.showError("잘못된 선택입니다.");
                    break;
            }
        }
    }

    private void applyLeave() {
        Leave l = view.inputNewLeave();
        if (service.applyLeave(l)) view.showMessage("신청 완료");
        else view.showError("신청 실패");
    }

    private void approveReject() {
        long id = view.inputLeaveId();
        boolean ok = view.confirmApprove()
                ? service.approveLeave(id, view.inputApproverId())
                : service.rejectLeave(id, view.inputApproverId());
        view.showMessage(ok ? "처리 완료" : "처리 실패");
    }

    private void history() {
        List<Leave> list = service.getByEmployee(view.inputEmployeeId());
        view.showHistory(list);
    }

    private void cancel() {
        if (service.cancelLeave(view.inputLeaveId()))
            view.showMessage("취소 완료");
        else view.showError("취소 실패");
    }
    public void run() {
        leaveMenu();
    }
    }

