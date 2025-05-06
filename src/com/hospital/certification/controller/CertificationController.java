package com.hospital.certification.controller;



import com.hospital.certification.model.entity.Certification;
import com.hospital.certification.model.service.CertificationService;
import com.hospital.certification.view.CertificationView;
public class CertificationController {
    private final CertificationService service = new CertificationService();
    private final CertificationView view = new CertificationView();

    public void certificationMenu() {
        while (true) {
            view.showMenu();
            int menu = view.inputMenu();
            switch (menu) {
                case 1:
                    view.showList(service.getAll());
                    break;
                case 2:
                    service.create(view.inputCertification());
                    break;
                case 3:
                    service.modify(view.inputCertificationForUpdate());
                    break;
                case 4:
                    Certification cert = view.inputCertificationForDelete();
                    if (cert != null) {
                        service.remove(cert.getCertId());
                    }
                    break;
                case 0:
                    return;
                default:
                    view.showError("잘못된 선택입니다.");
            }
        }
    }
}
