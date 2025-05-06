package humanResource.employee.view;

import common.view.HospitalBannerUtils;

public class EmployeeView {

    public void employeeMenu() {
        System.out.println();
        HospitalBannerUtils.printEmployeeBanner();
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 직원 조회");
        System.out.println("2️⃣ 직원 정보 수정");
        System.out.println("3️⃣ 직원 삭제");
        System.out.println();
        System.out.print("⏩ ");
    }

    public void searchEmployeeMenu() {
        System.out.println();
        HospitalBannerUtils.printSearchBanner();
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 이름으로 검색");
        System.out.println("2️⃣ 사번으로 검색");
        System.out.println("3️⃣ 부서 ID로 검색");
        System.out.println();
        System.out.print("⏩ ");
    }
}
