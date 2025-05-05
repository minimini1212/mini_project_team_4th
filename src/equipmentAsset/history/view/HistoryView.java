package equipmentAsset.history.view;

import java.sql.Date;
import java.sql.ResultSet;

public class HistoryView {

    /** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 사용 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

    // 이력 관리 메인 메뉴
    public void historyMenu() {
        System.out.println("---- 장비 이력 관리 ----");
        System.out.println("0. 이전 메뉴 돌아가기");
        System.out.println("1. 이력 통합 조회");
        System.out.println("2. 점검 이력 조회");
        System.out.println("3. 수리 이력 조회");
        System.out.println("4. 폐기 이력 조회");
        System.out.print("번호 입력 : ");
    }

    // 이력 통합 조회 메뉴
    public void integratedHistoryMenu() {
        System.out.println("---- 이력 통합 조회 ----");
        System.out.println("0. 이전 메뉴 돌아가기");
        System.out.println("1. 모든 이력 조회");
        System.out.println("2. 장비 번호로 조회");
        System.out.print("번호 입력 : ");
    }

    // 점검 이력 조회 메뉴
    public void inspectionHistoryMenu() {
        System.out.println("---- 점검 이력 조회 ----");
        System.out.println("0. 이전 메뉴 돌아가기");
        System.out.println("1. 모든 점검 이력 조회");
        System.out.println("2. 장비 번호로 조회");
        System.out.println("3. 점검 결과별 조회");
        System.out.print("번호 입력 : ");
    }

    // 수리 이력 조회 메뉴
    public void repairHistoryMenu() {
        System.out.println("---- 수리 이력 조회 ----");
        System.out.println("0. 이전 메뉴 돌아가기");
        System.out.println("1. 모든 수리 이력 조회");
        System.out.println("2. 장비 번호로 조회");
        System.out.println("3. 이력 유형별 조회");
        System.out.print("번호 입력 : ");
    }

    // 폐기 이력 조회 메뉴
    public void disposalHistoryMenu() {
        System.out.println("---- 폐기 이력 조회 ----");
        System.out.println("0. 이전 메뉴 돌아가기");
        System.out.println("1. 모든 폐기 이력 조회");
        System.out.println("2. 장비 번호로 조회");
        System.out.print("번호 입력 : ");
    }

    // 이력 유형 선택 메뉴
    public void getHistoryTypeMenu() {
        System.out.println("---- 이력 유형 선택 ----");
        System.out.println("1. 점검완료");
        System.out.println("2. 수리완료");
        System.out.println("3. 폐기");
        System.out.print("번호 입력 : ");
    }

    // 점검 결과 유형 선택 메뉴
    public void getInspectionResultTypeMenu() {
        System.out.println("---- 점검 결과 유형 선택 ----");
        System.out.println("1. 양호");
        System.out.println("2. 조치필요");
        System.out.print("번호 입력 : ");
    }

    // 수리 결과 유형 선택 메뉴
    public void getRepairResultTypeMenu() {
        System.out.println("---- 수리 결과 유형 선택 ----");
        System.out.println("1. 수리완료");
        System.out.println("2. 수리불가");
        System.out.print("번호 입력 : ");
    }

    /** =-=-=-=-=-=-=-=-=-=-=-= DAO 사용 메소드 =-=-=-=-=-=-=-=-=-=-=-= **/

    // 이력 목록 출력
    public void displayHistory(ResultSet rs) {
        try {
            System.out.printf("%-6s\t%-7s\t%-22s\t%-12s\t%-12s\t%-10s\t%-40s\n",
                    "이력ID", "장비ID", "장비명", "이력유형", "발생일자", "관련ID", "설명");
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int historyId = rs.getInt("history_id");
                int equipmentId = rs.getInt("equipment_id");
                String equipmentName = rs.getString("equipment_name");
                String historyType = rs.getString("history_type");
                Date occurrenceDate = rs.getDate("occurrence_date");
                Integer relatedId = rs.getInt("related_id");
                if (rs.wasNull()) relatedId = null;
                String description = rs.getString("description");

                System.out.printf("%-6d\t%-7d\t%-22s\t%-12s\t%-12s\t%-10s\t%-40s\n",
                        historyId, equipmentId, equipmentName, historyType, occurrenceDate,
                        (relatedId != null ? relatedId : "-"), description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 이력 상세 정보 출력 (점검, 수리 등 연관 정보 포함)
    public void displayHistoryDetail(ResultSet rs) {
        try {
            System.out.printf("%-6s\t%-7s\t%-22s\t%-12s\t%-12s\t%-10s\t%-40s\t%-30s\n",
                    "이력ID", "장비ID", "장비명", "이력유형", "발생일자", "관련ID", "설명", "상세정보");
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int historyId = rs.getInt("history_id");
                int equipmentId = rs.getInt("equipment_id");
                String equipmentName = rs.getString("equipment_name");
                String historyType = rs.getString("history_type");
                Date occurrenceDate = rs.getDate("occurrence_date");
                Integer relatedId = rs.getInt("related_id");
                if (rs.wasNull()) relatedId = null;
                String description = rs.getString("description");
                String additionalInfo = rs.getString("additional_info");  // 상세 정보 (점검 결과, 수리 내용 등)

                System.out.printf("%-6d\t%-7d\t%-22s\t%-12s\t%-12s\t%-10s\t%-40s\t%-30s\n",
                        historyId, equipmentId, equipmentName, historyType, occurrenceDate,
                        (relatedId != null ? relatedId : "-"), description,
                        (additionalInfo != null ? additionalInfo : "-"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
