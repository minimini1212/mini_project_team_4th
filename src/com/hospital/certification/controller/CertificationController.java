package com.hospital.certification.controller;

import com.hospital.certification.model.entity.Certification;
import com.hospital.certification.model.entity.EmployeeCertification;
import com.hospital.certification.model.service.CertificationService;
import com.hospital.certification.model.service.EmployeeCertificationService;
import com.hospital.certification.view.CertificationView;
import com.hospital.certification.view.EmployeeCertificationView;

public class CertificationController {
    private final CertificationService certSvc = new CertificationService();
    private final EmployeeCertificationService ecSvc = new EmployeeCertificationService();
    private final CertificationView certView = new CertificationView();
    private final EmployeeCertificationView ecView = new EmployeeCertificationView();

    public void start() {
        while(true) {
            int menu = certView.showMainMenu();
            switch(menu) {
                case 1 -> certView.showList(certSvc.getAll());
                case 2 -> certSvc.create(certView.inputCertification());
                case 3 -> certSvc.modify(certView.inputCertificationForUpdate());
                case 4 -> certSvc.remove(certView.inputCertId());
                case 5 -> ecView.showList(ecSvc.getByEmployee(ecView.inputEmployeeId()));
                case 6 -> ecSvc.addCertification(ecView.inputEmployeeCertification());
                case 7 -> ecSvc.removeCertification(ecView.inputEmpCertId());
                case 0 -> { return; }
                default -> certView.showError("잘못된 선택입니다.");
            }
        }
    }
}