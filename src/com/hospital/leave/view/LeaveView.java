package com.hospital.leave.view;

import com.hospital.leave.model.entity.Leave;
import common.view.HospitalBannerUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LeaveView {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println();
        HospitalBannerUtils.printVacationBanner();
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 휴가 신청");
        System.out.println("2️⃣ 휴가 승인/반려");
        System.out.println("3️⃣ 휴가 현황 조회");
        System.out.println("4️⃣ 휴가 취소");
        System.out.println();
        System.out.print("⏩ ");
    }

    public int inputMenu() {
        try { return Integer.parseInt(scanner.nextLine()); }
        catch (Exception e) { return -1; }
    }

    public Leave inputNewLeave() {
        System.out.print("사번: "); String empId = scanner.nextLine();
        System.out.print("휴가 종류: "); String type = scanner.nextLine();
        System.out.print("시작일 (YYYY-MM-DD): "); LocalDate sd = LocalDate.parse(scanner.nextLine());
        System.out.print("종료일 (YYYY-MM-DD): "); LocalDate ed = LocalDate.parse(scanner.nextLine());
        System.out.print("일수: "); double days = Double.parseDouble(scanner.nextLine());
        System.out.print("사유: "); String reason = scanner.nextLine();
        System.out.print("승인자 사번: "); String apr = scanner.nextLine();
        LocalDate ap = LocalDate.now();
        return new Leave(null, empId, type, sd, ed, days, reason, "REQUEST", apr, ap);
    }

    public long inputLeaveId() {
        System.out.print("휴가 ID: "); return Long.parseLong(scanner.nextLine());
    }

    public boolean confirmApprove() {
        System.out.print("승인하시겠습니까? (Y/N): ");
        return scanner.nextLine().equalsIgnoreCase("Y");
    }

    public String inputApproverId() {
        System.out.print("승인자 사번: "); return scanner.nextLine();
    }

    public String inputEmployeeId() {
        System.out.print("조회할 사번: "); return scanner.nextLine();
    }

    public long inputDepartmentId() {
        System.out.print("조회할 부서ID: "); return Long.parseLong(scanner.nextLine());
    }

    public int inputYear() {
        System.out.print("연도: "); return Integer.parseInt(scanner.nextLine());
    }

    public int inputMonth() {
        System.out.print("월: "); return Integer.parseInt(scanner.nextLine());
    }

    public void showHistory(List<Leave> list) {
        list.forEach(l -> System.out.println(l));
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showError(String err) {
        System.err.println("Error: " + err);
    }
}
