package com.hospital.certification.model.test;

import com.hospital.certification.model.entity.Certification;
import com.hospital.certification.model.service.CertificationService;

import java.util.List;

public class CertificationTest {
    public static void main(String[] args) {
        CertificationService service = new CertificationService();

        // 1. 전체 조회
        System.out.println("=== 전체 자격증 목록 ===");
        List<Certification> allCerts = service.getAll();
        allCerts.forEach(System.out::println);

        // 2. 신규 등록
        Certification newCert = new Certification(
                null,
                "테스트자격증",
                "테스트기관",
                "테스트용 자격증 설명"
        );
        boolean created = service.create(newCert);
        System.out.println("\n[CREATE] 성공 여부: " + created);

        // 3. 등록 후 전체 조회
        System.out.println("\n=== 등록 후 전체 목록 ===");
        allCerts = service.getAll();
        allCerts.forEach(System.out::println);

        // 4. 방금 등록된 자격증 ID 추출 (리스트 마지막 항목이라 가정)
        Long lastId = allCerts.get(allCerts.size() - 1).getCertId();

        // 5. ID로 단건 조회
        System.out.println("\n[GET BY ID] id=" + lastId);
        Certification fetched = service.getById(lastId);
        System.out.println(fetched);

        // 6. 수정
        fetched.setDescription("수정된 설명입니다.");
        boolean updated = service.modify(fetched);
        System.out.println("\n[UPDATE] 성공 여부: " + updated);

        // 7. 수정 확인
        System.out.println("\n[GET BY ID] 수정 확인 id=" + lastId);
        System.out.println(service.getById(lastId));

        // 8. 삭제
        boolean deleted = service.remove(lastId);
        System.out.println("\n[DELETE] 성공 여부: " + deleted);

        // 9. 최종 전체 목록 확인
        System.out.println("\n=== 최종 전체 자격증 목록 ===");
        allCerts = service.getAll();
        allCerts.forEach(System.out::println);
    }
}