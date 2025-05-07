package common;

import humanResource.employee.model.entity.Employee;



public class SessionContext {
    private static Employee loggedInUser;
    private static String role;  // 예: "master", "admin", "basic"
    private static int rankOrder;
    private static int deptId;
    private static String empNumber;

    private SessionContext() {}  // 인스턴스 생성 방지

    public static void set(Employee user, String userRole) {
        loggedInUser = user;
        role = userRole;
    }

    public static Employee getUser() {
        return loggedInUser;
    }

    public static String getRole() {
        return role;
    }

    public static int getRankOrder() {
        return rankOrder;
    }
    public static void setRankOrder(int rankOrder) {
        SessionContext.rankOrder = rankOrder;
    }
    public static int getDeptId() {
        return deptId;
    }
    public static void setDeptId(int deptId) {
        SessionContext.deptId = deptId;
    }
    public static String getEmpNumber() {
        return empNumber;
    }
    public static void setEmpNumber(String empNumber){
        SessionContext.empNumber = empNumber;
    }



    public static void clear() {
        loggedInUser = null;
        role = null;
        rankOrder = 0;
        deptId = 0;
        empNumber = null;

    }

    public static boolean isLoggedIn() {
        return loggedInUser != null;
    }
}

