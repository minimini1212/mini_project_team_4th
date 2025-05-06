package equipmentAsset.repair.view;

import java.sql.Date;
import java.sql.ResultSet;

public class RepairView {

    /** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 사용 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

    // 최상위 수리 관리 메뉴
    public void repairMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝙍 𝙀 𝙋 𝘼 𝙄 𝙍   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 수리 요청 관리");
        System.out.println("2️⃣ 수리 결과 관리");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 요청 관리 메뉴
    public void repairRequestMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌  𝙍 𝙀 𝙌 𝙐 𝙀 𝙎 𝙏  ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 수리 요청 등록");
        System.out.println("2️⃣ 수리 요청 조회");
        System.out.println("3️⃣ 수리 요청 수정");
        System.out.println("4️⃣ 수리 요청 삭제");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 요청 등록 메뉴
    public void saveRepairRequestMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝙍 𝙀 𝙂 𝙄 𝙎 𝙏   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 수리 요청 등록");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 요청 조회 메뉴
    public void findRepairRequestMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝙎 𝙀 𝘼 𝙍 𝘾 𝙃   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 모든 수리 요청 조회");
        System.out.println("2️⃣ 장비 번호로 조회");
        System.out.println("3️⃣ 요청 번호로 조회");
        System.out.println("4️⃣ 상태별 조회");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 요청 수정 메뉴
    public void updateRepairRequestMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝙐 𝙋 𝘿 𝘼 𝙏 𝙀   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 수리 요청 수정");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 요청 삭제 메뉴
    public void deleteRepairRequestMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝘿 𝙀 𝙇 𝙀 𝙏 𝙀   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 수리 요청 삭제");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 관리 메뉴
    public void repairResultMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌  𝙍 𝙀 𝙎 𝙐 𝙇 𝙏 𝙎  ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 수리 결과 등록");
        System.out.println("2️⃣ 수리 결과 조회");
        System.out.println("3️⃣ 수리 결과 수정");
        System.out.println("4️⃣ 수리 결과 삭제");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 등록 메뉴
    public void saveRepairResultMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝙍 𝙀 𝙂 𝙄 𝙎 𝙏   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 수리 결과 등록");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 조회 메뉴
    public void findRepairResultMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝙎 𝙀 𝘼 𝙍 𝘾 𝙃   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 모든 수리 결과 조회");
        System.out.println("2️⃣ 장비 번호로 조회");
        System.out.println("3️⃣ 요청 번호로 조회");
        System.out.println("4️⃣ 결과 유형별 조회");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 수정 메뉴
    public void updateRepairResultMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝙐 𝙋 𝘿 𝘼 𝙏 𝙀   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 수리 결과 수정");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 삭제 메뉴
    public void deleteRepairResultMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝘿 𝙀 𝙇 𝙀 𝙏 𝙀   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 수리 결과 삭제");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 요청 수정 항목 메뉴
    public void updateRepairRequestItemMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝙐 𝙋 𝘿 𝘼 𝙏 𝙀   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 요청 일자 수정");
        System.out.println("2️⃣ 고장 증상 수정");
        System.out.println("3️⃣ 요청 상태 수정");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 수정 항목 메뉴
    public void updateRepairResultItemMenu() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝙐 𝙋 𝘿 𝘼 𝙏 𝙀   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 수리 내용 수정");
        System.out.println("2️⃣ 수리 비용 수정");
        System.out.println("3️⃣ 결과 유형 수정");
        System.out.println();
        System.out.print("⏩ ");
    }

    /** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 안내 출력 =-=-=-=-=-=-=-=-=-=-=-= **/

    // 수리 요청 상태 선택 출력
    public void getRepairRequestStatus() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌   𝙎 𝙏 𝘼 𝙏 𝙐 𝙎   ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("1️⃣ 예정");
        System.out.println("2️⃣ 완료");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 유형 선택 출력
    public void getRepairResultType() {
        System.out.println();
        System.out.println("  ▌│█║▌║▌  𝙍 𝙀 𝙎 𝙐 𝙇 𝙏 𝙎  ▌║▌║█│▌");
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        System.out.println("1️⃣ 수리완료");
        System.out.println("2️⃣ 수리불가");
        System.out.println();
        System.out.print("⏩ ");
    }

    /** =-=-=-=-=-=-=-=-=-=-=-= DAO 사용 메소드 =-=-=-=-=-=-=-=-=-=-=-= **/

    // 수리 요청 목록 출력
    public void displayRepairRequests(ResultSet rs) {
        try {
            System.out.printf("%-6s\t%-22s\t%-12s\t%-15s\t%-30s\n", 
                    "요청ID", "장비명", "요청일자", "요청상태", "고장증상");
            System.out.println(
                    "---------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int requestId = rs.getInt("request_id");
                String equipmentName = rs.getString("equipment_name");
                Date requestDate = rs.getDate("request_date");
                String status = rs.getString("status");
                String failureSymptom = rs.getString("failure_symptom");

                System.out.printf("%-6d\t%-22s\t%-12s\t%-15s\t%-30s\n",
                        requestId, equipmentName, requestDate, status, failureSymptom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 수리 결과 목록 출력
    public void displayRepairResults(ResultSet rs) {
        try {
            System.out.printf("%-6s\t%-6s\t%-22s\t%-12s\t%-40s\t%-10s\t%-15s\n", 
                    "결과ID", "요청ID", "장비명", "수리비용", "수리내용", "결과유형", "최종수정일");
            System.out.println(
                    "------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int resultId = rs.getInt("result_id");
                int requestId = rs.getInt("request_id");
                String equipmentName = rs.getString("equipment_name");
                int repairCost = rs.getInt("repair_cost");
                String repairContent = rs.getString("repair_content");
                String result = rs.getString("repair_result");
                Date lastUpdatedDate = rs.getDate("last_updated_date");

                System.out.printf("%-6d\t%-6d\t%-22s\t%,10d원\t%-40s\t%-10s\t%-15s\n",
                        resultId, requestId, equipmentName, repairCost, repairContent, result, lastUpdatedDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}