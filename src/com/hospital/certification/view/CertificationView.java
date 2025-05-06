package com.hospital.certification.view;

import com.hospital.certification.model.entity.Certification;
import com.hospital.certification.model.service.CertificationService;
import common.view.HospitalBannerUtils;

import java.util.List;
import java.util.Scanner;

public class CertificationView {
    private final Scanner scanner = new Scanner(System.in);
    private final CertificationService service = new CertificationService();
    public void showMenu() {
        System.out.println();
        HospitalBannerUtils.printCertificateBanner();
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 전체 자격증 조회");
        System.out.println("2️⃣ 자격증 등록");
        System.out.println("3️⃣ 자격증 수정");
        System.out.println("4️⃣ 자격증 삭제");
        System.out.println();
        System.out.print("⏩ ");
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
        System.out.print("수정할 자격증 ID 또는 이름: ");
        String input = scanner.nextLine();

        Certification cert;
        try {
            long id = Long.parseLong(input);
            cert = service.getById(id);
        } catch (NumberFormatException e) {
            cert = service.getByName(input);
        }

        if (cert == null) {
            System.out.println("❌ 해당 자격증을 찾을 수 없습니다.");
            return null;
        }

        System.out.println("🔎 기존 정보: " + cert);

        System.out.print("새 자격증 이름: ");
        String newName = scanner.nextLine();
        System.out.print("새 발급 기관: ");
        String newOrg = scanner.nextLine();
        System.out.print("새 설명: ");
        String newDesc = scanner.nextLine();

        return new Certification(cert.getCertId(), newName, newOrg, newDesc);
    }

    public Certification inputCertificationForDelete() {
        System.out.print("삭제할 자격증 ID 또는 이름: ");
        String input = scanner.nextLine();

        Certification cert;
        try {
            long id = Long.parseLong(input);
            cert = service.getById(id);
        } catch (NumberFormatException e) {
            cert = service.getByName(input);
        }

        if (cert == null) {
            System.out.println("❌ 해당 자격증을 찾을 수 없습니다.");
            return null;
        }

        System.out.println("🔎 삭제할 자격증: " + cert);
        return cert;
    }

    public void showError(String msg) {
        System.out.println("❌ " + msg);
    }
}