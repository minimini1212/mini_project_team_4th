package humanResource.employee.view;

public class EmployeeView {

    public void employeeMenu(){
        //            System.out.println("\n전체메뉴 > 인사관리 > 직원관리");
        System.out.println("===== 직원 관리 메뉴 =====");
        System.out.println("0. 뒤로 가기");
        System.out.println("1. 직원 조회");
        System.out.println("2. 직원 정보 수정");
        System.out.println("3. 직원 삭제");
        System.out.print("선택: ");

    }

    public void searchEmployeeMenu(){
//        System.out.println("\n전체메뉴 > 인사관리 > 직원관리 > 직원검색");
        System.out.println("===== 직원 검색 =====");
        System.out.println("0. 뒤로 가기");
        System.out.println("1. 이름으로 검색");
        System.out.println("2. 사번으로 검색");
        System.out.println("3. 부서 ID로 검색");
        System.out.print("선택: ");
    }
}
