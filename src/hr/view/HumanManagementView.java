package hr.view;

import dbConn.ConnectionSingletonHelper;
import hr.department.model.dao.DepartmentDao;
import hr.employee.model.dao.EmployeeDao;
import hr.employee.model.entity.Employee;
import hr.userAccount.model.dao.UserAccountDao;
import hr.userAccount.model.service.UserAccountService;

import java.sql.Connection;
import java.util.Scanner;

public class HumanManagementView {
    private final Connection conn;

    public HumanManagementView(Connection conn){
        this.conn = conn;
    }

    public void run(Scanner scanner, String role, Employee emp) {

    }
}
