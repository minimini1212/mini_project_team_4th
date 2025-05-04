package com.hospital.certification.view;

import com.hospital.certification.model.entity.Certification;

import java.util.List;
import java.util.Scanner;

public class CertificationView {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\n===== 자격증 관리 =====");
        System.out.println("1. 전체 자격증 조회");
        System.out.println("2. 자격증 등록");
        System.out.println("3. 자격증 수정");
        System.out.println("4. 자격증 삭제");
        System.out.println("0. 이전 메뉴");
        System.out.print("선택: ");
    }

    public int inputMenu() {
        return Integer.parseInt(scanner.nextLine());
    }

    public void showList(List<Certification> list) {
        System.out.println("--- 자격증 목록 ---");
        for (Certification c : list) {
            System.out.println(c);
        }
    }

    public Certification inputCertification() {
        System.out.println("===== 자격증 등록 =====");
        Certification c = new Certification();
        System.out.print("자격증 이름: ");
        c.setCertName(scanner.nextLine());
        System.out.print("발급 기관: ");
        c.setIssuingOrg(scanner.nextLine());
        System.out.print("설명: ");
        c.setDescription(scanner.nextLine());
        return c;
    }

    public Certification inputCertificationForUpdate() {
        System.out.println("===== 자격증 수정 =====");
        Certification c = new Certification();
        System.out.print("수정할 자격증 ID: ");
        c.setCertId(Long.parseLong(scanner.nextLine()));
        System.out.print("새 자격증 이름: ");
        c.setCertName(scanner.nextLine());
        System.out.print("새 발급 기관: ");
        c.setIssuingOrg(scanner.nextLine());
        System.out.print("새 설명: ");
        c.setDescription(scanner.nextLine());
        return c;
    }

    public long inputCertId() {
        System.out.print("삭제할 자격증 ID: ");
        return Long.parseLong(scanner.nextLine());
    }

    public void showError(String msg) {
        System.out.println("❌ " + msg);
    }
}