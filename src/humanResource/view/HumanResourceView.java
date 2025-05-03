package humanResource.view;

import humanResource.employee.model.entity.Employee;


import java.sql.Connection;
import java.util.Scanner;

public class HumanResourceView {
    private final Connection conn;

    public HumanResourceView(Connection conn){
        this.conn = conn;
    }

    public void run(Scanner scanner, String role, Employee emp) {

    }
}
