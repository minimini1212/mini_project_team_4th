package com.hospital.certification.view;

import com.hospital.certification.model.entity.EmployeeCertification;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmployeeCertificationView {
    private final Scanner sc = new Scanner(System.in);

    public Long inputEmployeeId() {
        System.out.print("조회할 직원 ID: ");
        return Long.parseLong(sc.nextLine());
    }

    public void showList(List<EmployeeCertification> list) {
        System.out.println("ID | 직원ID | 자격증ID | 취득일 | 만료일 | 번호 | 갱신여부 | 알림일");
        list.forEach(ec -> System.out.printf(
                "%d | %d | %d | %s | %s | %s | %s | %s\n",
                ec.getEmpCertId(),
                ec.getEmployeeId(),
                ec.getCertId(),
                ec.getAcquisitionDate(),
                ec.getExpiryDate(),
                ec.getCertNumber(),
                ec.isRenewalRequired() ? "Y" : "N",
                ec.getAlertDate()
        ));
    }

    public EmployeeCertification inputEmployeeCertification() {
        System.out.print("직원 ID: ");
        Long empId = Long.parseLong(sc.nextLine());
        System.out.print("자격증 ID: ");
        Long certId = Long.parseLong(sc.nextLine());
        System.out.print("취득일 (YYYY-MM-DD): ");
        LocalDate acq = LocalDate.parse(sc.nextLine());
        System.out.print("만료일 (YYYY-MM-DD): ");
        LocalDate exp = LocalDate.parse(sc.nextLine());
        System.out.print("자격증 번호: ");
        String num = sc.nextLine();
        System.out.print("갱신 필요? (Y/N): ");
        boolean renew = sc.nextLine().equalsIgnoreCase("Y");
        LocalDate alert = exp.minusDays(30);
        return new EmployeeCertification(null, empId, certId, acq, exp, num, renew, alert);
    }

    public Long inputEmpCertId() {
        System.out.print("삭제할 직원-자격증 ID: ");
        return Long.parseLong(sc.nextLine());
    }
}