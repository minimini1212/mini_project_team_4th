package humanResource.common.controller;

import com.hospital.certification.controller.CertificationController;
import com.hospital.leave.controller.LeaveController;
import humanResource.common.view.HumanResourceView;
import humanResource.employee.controller.EmployeeController;


import java.util.Scanner;

public class HumanResourceController {
    private HumanResourceView humanResourceView = new HumanResourceView();
    private EmployeeController employeeController = new EmployeeController();
    private CertificationController certificationController = new CertificationController();
    private LeaveController leaveController = new LeaveController();

    public void humanResourceMenu(Scanner sc, int rankOrder){
        while(true){
            humanResourceView.humanResourceMenu();
            switch (sc.nextLine()){
                case "0":
                    return;
                case "1":
                    employeeController.run();
                    break;
                case "2":
                    certificationController.run();
                    break;
                case "3":
                    leaveController.run();
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }
}
