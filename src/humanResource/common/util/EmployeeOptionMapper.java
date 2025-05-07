package humanResource.common.util;

import java.util.HashMap;
import java.util.Map;

public class EmployeeOptionMapper {

    public static final Map<Integer, String> POSITION_MAP = new HashMap<>();
    public static final Map<Integer, String> JOB_MAP = new HashMap<>();
    public static final Map<Integer, String> STATUS_MAP = new HashMap<>();
    public static final Map<Integer, String> DEPARTMENT_MAP = new HashMap<>();


    static {
        POSITION_MAP.put(1, "병원장");
        POSITION_MAP.put(2, "부장");
        POSITION_MAP.put(3, "차장");
        POSITION_MAP.put(4, "과장");
        POSITION_MAP.put(5, "대리");
        POSITION_MAP.put(6, "사원");
        POSITION_MAP.put(7, "인턴");

        JOB_MAP.put(1, "경영관리");
        JOB_MAP.put(2, "인사담당");
        JOB_MAP.put(3, "급여담당");
        JOB_MAP.put(4, "재무담당");
        JOB_MAP.put(5, "회계담당");
        JOB_MAP.put(6, "장비관리");
        JOB_MAP.put(7, "장비점검");
        JOB_MAP.put(10, "일반행정");

        STATUS_MAP.put(1, "재직");
        STATUS_MAP.put(2, "휴직");
        STATUS_MAP.put(3, "퇴직");

        DEPARTMENT_MAP.put(1, "병원장실");
        DEPARTMENT_MAP.put(2, "인사관리부서");
        DEPARTMENT_MAP.put(3, "예산·회계관리부서");
        DEPARTMENT_MAP.put(4, "자산관리부서");
    }

    public static String getPositionName(int id) {
        return POSITION_MAP.getOrDefault(id, "알 수 없음");
    }

    public static String getJobName(int id) {
        return JOB_MAP.getOrDefault(id, "알 수 없음");
    }

    public static String getStatusName(String status) {
        return status != null ? status : "알 수 없음";
    }

    public static String getDepartmentName(int id) {
        return DEPARTMENT_MAP.getOrDefault(id, "알 수 없음");
    }


    public static void printPositionOptions() {
        POSITION_MAP.forEach((key, value) -> System.out.println(key + ". " + value));
    }

    public static void printJobOptions() {
        JOB_MAP.forEach((key, value) -> System.out.println(key + ". " + value));
    }

    public static void printStatusOptions() {
        STATUS_MAP.forEach((key, value) -> System.out.println(key + ". " + value));
    }
}

