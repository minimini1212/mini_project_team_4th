package com.hospital.certification.view;

import com.hospital.certification.model.entity.EmployeeCertification;

import java.util.List;
import java.util.Scanner;

public class EmployeeCertificationView {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌ 𝙀𝙈𝙋 𝘾𝙀𝙍𝙏𝙄𝙁𝙄𝘾𝘼𝙏𝙀 ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 전체 자격증 내역 조회");
        System.out.println("2️⃣ 사원별 자격증 조회");
        System.out.println("3️⃣ 자격증 등록");
        System.out.println("4️⃣ 자격증 정보 수정");
        System.out.println("5️⃣ 자격증 삭제");
        System.out.println();
        System.out.print("⏩ ");
    }
    public int inputMenu() {
        return Integer.parseInt(scanner.nextLine());
    }

    public void showList(List<EmployeeCertification> list) {
        System.out.println("--- 자격증 보유 현황 ---");
        for (EmployeeCertification ec : list) {
            System.out.println(ec);
        }
    }

    public EmployeeCertification inputForCreate() {
        System.out.println("===== 자격증 등록 =====");
        EmployeeCertification ec = new EmployeeCertification();
        System.out.print("사원 ID: ");
        ec.setEmployeeId(Long.parseLong(scanner.nextLine()));
        System.out.print("자격증 ID: ");
        ec.setCertId(Long.parseLong(scanner.nextLine()));
        System.out.print("취득일 (YYYY-MM-DD): ");
        ec.setAcquisitionDate(java.time.LocalDate.parse(scanner.nextLine()));
        System.out.print("만료일 (YYYY-MM-DD): ");
        ec.setExpiryDate(java.time.LocalDate.parse(scanner.nextLine()));
        System.out.print("자격 번호: ");
        ec.setCertNumber(scanner.nextLine());
        System.out.print("갱신 필요 여부 (Y/N): ");
        ec.setRenewalRequired(scanner.nextLine());
        System.out.print("알림일 (YYYY-MM-DD): ");
        ec.setAlertDate(java.time.LocalDate.parse(scanner.nextLine()));
        return ec;
    }

    public EmployeeCertification inputForUpdate() {
        System.out.println("===== 자격증 정보 수정 =====");
        EmployeeCertification ec = new EmployeeCertification();
        System.out.print("사원 ID: ");
        ec.setEmpCertId(Long.parseLong(scanner.nextLine()));
        System.out.print("새 취득일 (YYYY-MM-DD): ");
        ec.setAcquisitionDate(java.time.LocalDate.parse(scanner.nextLine()));
        System.out.print("새 만료일 (YYYY-MM-DD): ");
        ec.setExpiryDate(java.time.LocalDate.parse(scanner.nextLine()));
        System.out.print("새 갱신 필요 여부 (Y/N): ");
        ec.setRenewalRequired(scanner.nextLine());
        System.out.print("새 알림일 (YYYY-MM-DD): ");
        ec.setAlertDate(java.time.LocalDate.parse(scanner.nextLine()));
        return ec;
    }

    public long inputEmpCertId() {
        System.out.print("자격증 삭제할 사원 ID: ");
        return Long.parseLong(scanner.nextLine());
    }

    public long inputEmployeeId() {
        System.out.print("조회할 사원 ID: ");
        return Long.parseLong(scanner.nextLine());
    }

    public void showError(String msg) {
        System.out.println("❌ " + msg);
    }
}