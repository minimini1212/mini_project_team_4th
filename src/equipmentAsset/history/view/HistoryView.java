package equipmentAsset.history.view;

import java.sql.Date;
import java.sql.ResultSet;

public class HistoryView {

    /** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 사용 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

    // 이력 관리 메인 메뉴
    public void historyAdminMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌  𝙃 𝙄 𝙎 𝙏 𝙊 𝙍 𝙔  ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 이력 통합 조회");
        System.out.println("2️⃣ 점검 이력 조회");
        System.out.println("3️⃣ 수리 이력 조회");
        System.out.println("4️⃣ 폐기 이력 조회");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 이력 통합 조회 메뉴
    public void integratedHistoryMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌  𝙄 𝙉 𝙏 𝙀 𝙂 𝙍 𝘼 𝙏 𝙀 𝘿   ▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 모든 이력 조회");
        System.out.println("2️⃣ 장비 번호로 조회");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 점검 이력 조회 메뉴
    public void inspectionHistoryMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌   𝙄 𝙉 𝙎 𝙋 𝙀 𝘾 𝙏 𝙄 𝙊 𝙉   ▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 모든 점검 이력 조회");
        System.out.println("2️⃣ 장비 번호로 조회");
        System.out.println("3️⃣ 점검 결과별 조회");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 이력 조회 메뉴
    public void repairHistoryMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝙍 𝙀 𝙋 𝘼 𝙄 𝙍   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 모든 수리 이력 조회");
        System.out.println("2️⃣ 장비 번호로 조회");
        System.out.println("3️⃣ 이력 유형별 조회");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 폐기 이력 조회 메뉴
    public void disposalHistoryMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌ 𝘿 𝙄 𝙎 𝙋 𝙊 𝙎 𝘼 𝙇 ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 모든 폐기 이력 조회");
        System.out.println("2️⃣ 장비 번호로 조회");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 이력 유형 선택 메뉴
    public void getHistoryTypeMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌     𝙏 𝙔 𝙋 𝙀     ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("1️⃣ 점검완료");
        System.out.println("2️⃣ 수리완료");
        System.out.println("3️⃣ 폐기");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 점검 결과 유형 선택 메뉴
    public void getInspectionResultTypeMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝙍 𝙀 𝙎 𝙐 𝙇 𝙏   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("1️⃣ 양호");
        System.out.println("2️⃣ 조치필요");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 유형 선택 메뉴
    public void getRepairResultTypeMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝙍 𝙀 𝙎 𝙐 𝙇 𝙏   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("1️⃣ 수리완료");
        System.out.println("2️⃣ 수리불가");
        System.out.println();
        System.out.print("⏩ ");
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
