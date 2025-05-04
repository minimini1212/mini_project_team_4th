package humanResource.common.controller;

import humanResource.common.view.HumanResourceView;
import humanResource.employee.controller.EmployeeController;


import java.util.Scanner;

public class HumanResourceController {
    private HumanResourceView humanResourceView = new HumanResourceView();
    private EmployeeController employeeController = new EmployeeController();

    public void humanResourceMenu(Scanner sc){
        while(true){
            humanResourceView.humanResourceMenu();
            switch (sc.nextLine()){
                case "0":
                    return;
                case "1":
                    employeeController.run();
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }


}
