package humanResource.common.util;

import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;

public class EmpNumberGenerator {
    private static String getPhoneTail(String phone) {
        if (phone == null) return "0000";
        String[] parts = phone.split("-");
        return parts[parts.length - 1];
    }

    private static String getHireYear(Date hireDate) {
        LocalDate local = hireDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return String.valueOf(local.getYear());
    }

    public static String generateEmpNumber(Date hireDate, int departmentId, String phone) {
        return getHireYear(hireDate) + "0" + departmentId + getPhoneTail(phone);
    }
}
