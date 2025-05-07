package humanResource.employee.model.entity;

import common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity {
    private int employeeId;
    private String empNumber;
    private String name;
    private Date birthDate;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private Date hireDate;
    private Date resignDate;
    private String status;
    private String empType;

    private int departmentId;
    private int positionId;
    private int jobId;

    private String departmentName;
    private String positionName;
    private String jobName;

    @Override
    public String toString() {
        return String.format("\n" +
                        "━━━━━━  👤 \033[1;36m직원 상세 정보\033[0m 👤 ━━━━━━\n" +
                        "\n" +
                        "  🔑 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  📋 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  📝 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🎂 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  ⚤ \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🏠 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  📞 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  📧 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  📅 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🚪 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🚦 \t\033[1;31m%-10s\033[0m\t: \t\033[1;%sm\"%s\"\033[0m\n" +
                        "  💼 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🏢 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  👑 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🛠️ \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━\n",
                " 직원 ID", employeeId,
                " 직원 번호", empNumber != null ? empNumber : "미입력",
                " 이름", name != null ? name : "미입력",
                " 생년월일", birthDate != null ? birthDate.toString() : "미입력",
                " 성별", gender != null ? gender : "미입력",
                " 주소", address != null ? address : "미입력",
                " 전화번호", phone != null ? phone : "미입력",
                " 이메일", email != null ? email : "미입력",
                " 입사일", hireDate != null ? hireDate.toString() : "미입력",
                " 퇴사일", resignDate != null ? resignDate.toString() : "미입력",
                " 상태", getStatusColor(status), status != null ? status : "미입력",
                " 고용형태", empType != null ? empType : "미입력",
                " 부서", departmentName != null ? departmentName : "미입력",
                " 직위", positionName != null ? positionName : "미입력",
                " 직무", jobName != null ? jobName : "미입력");
    }

    // 상태에 따른 색상 코드를 반환하는 메서드
    private String getStatusColor(String status) {
        if (status == null) return "37"; // 기본 흰색

        switch (status.toUpperCase()) {
            case "재직":
                return "32"; // 녹색
            case "휴직":
                return "33"; // 노란색
            case "퇴직":
                return "31"; // 빨간색
            default:
                return "37"; // 기본 흰색
        }
    }
}
