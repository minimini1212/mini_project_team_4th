package common;

import humanResource.employee.model.entity.Employee;

public class SessionContext {
    private static Employee currentUser;
    private static String currentRole;

    public static void set(Employee user, String role) {
        currentUser = user;
        currentRole = role;
    }

    public static Employee getUser() {
        return currentUser;
    }

    public static String getRole() {
        return currentRole;
    }

    public static void clear() {
        currentUser = null;
        currentRole = null;
    }
}

