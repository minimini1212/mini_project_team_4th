package com.hospital.certification.view;

import com.hospital.certification.model.entity.Certification;

import java.util.List;
import java.util.Scanner;

public class CertificationView {
    private final Scanner sc = new Scanner(System.in);

    public int showMainMenu() {
        System.out.println("\n=== 자격증 관리 ===");
        System.out.println("1. 자격증 목록");
        System.out.println("2. 자격증 등록");
        System.out.println("3. 자격증 수정");
        System.out.println("4. 자격증 삭제");
        System.out.println("5. 직원 자격증 조회");
        System.out.println("6. 직원 자격증 등록");
        System.out.println("7. 직원 자격증 삭제");
        System.out.println("0. 뒤로가기");
        System.out.print("> ");
        try {
            return Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            return -1;
        }
    }

    public void showList(List<Certification> list) {
        System.out.println("ID | 이름 | 발급기관 | 설명");
        list.forEach(c -> System.out.printf("%d | %s | %s | %s\n",
                c.getCertId(), c.getCertName(), c.getIssuingOrg(), c.getDescription()));
    }

    public Certification inputCertification() {
        System.out.print("자격증 이름: ");
        String name = sc.nextLine();
        System.out.print("발급 기관: ");
        String org = sc.nextLine();
        System.out.print("설명: ");
        String desc = sc.nextLine();
        return new Certification(null, name, org, desc);
    }

    public Certification inputCertificationForUpdate() {
        System.out.print("수정할 자격증 ID: ");
        Long id = Long.parseLong(sc.nextLine());
        Certification c = inputCertification();
        c.setCertId(id);
        return c;
    }

    public Long inputCertId() {
        System.out.print("삭제할 자격증 ID: ");
        return Long.parseLong(sc.nextLine());
    }

    public void showError(String msg) {
        System.err.println("Error: " + msg);
    }
}