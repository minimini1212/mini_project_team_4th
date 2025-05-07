package humanResource.common.controller;

import com.hospital.certification.controller.CertificationController;
import com.hospital.leave.controller.LeaveController;
import common.SessionContext;
import humanResource.common.view.HumanResourceView;
import humanResource.employee.controller.EmployeeController;
import main.view.MainEntry;


import java.util.Scanner;

public class HumanResourceController {
    private HumanResourceView humanResourceView = new HumanResourceView();
    private EmployeeController employeeController = new EmployeeController();
    private CertificationController certificationController = new CertificationController();
    private LeaveController leaveController = new LeaveController();

    public void humanResourceMenu(Scanner sc, int rankOrder){
        while(true){
            if (rankOrder == 1) {
                humanResourceView.humanResourceMenuForMaster();
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
            } else {
                humanResourceView.humanResourceView();
                switch (sc.nextLine()){
                    case "0":
                        if (confirmLogout()) {
                            SessionContext.clear();
                            System.out.println("✅ 로그아웃되었습니다. 로그인 메뉴로 돌아갑니다.\n");
                            MainEntry.main(null);
                            return;
                        }
                        continue;
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
    private boolean confirmLogout() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("로그아웃 하시겠습니까? (y/n): ");
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("y")) return true;
            if (input.equals("n")) return false;

            System.out.println("❌ 잘못된 입력입니다. y 또는 n을 입력해주세요.");
        }
    }
}
