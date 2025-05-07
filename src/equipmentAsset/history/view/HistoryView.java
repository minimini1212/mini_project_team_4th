package equipmentAsset.history.view;

import common.view.HospitalBannerUtils;

import java.sql.Date;
import java.sql.ResultSet;

public class HistoryView {

    /** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 사용 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

    // 이력 관리 메인 메뉴
    public void historyAdminMenu() {
        System.out.println();
        HospitalBannerUtils.printHistoryBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 이력 통합 조회");
        System.out.println();
        System.out.println("2️⃣ 점검 이력 조회");
        System.out.println();
        System.out.println("3️⃣ 수리 이력 조회");
        System.out.println();
        System.out.println("4️⃣ 폐기 이력 조회");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 이력 통합 조회 메뉴
    public void integratedHistoryMenu() {
        System.out.println();
        HospitalBannerUtils.printIntegratedBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 모든 이력 조회");
        System.out.println();
        System.out.println("2️⃣ 장비 번호로 조회");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 점검 이력 조회 메뉴
    public void inspectionHistoryMenu() {
        System.out.println();
        HospitalBannerUtils.printInspectionBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 모든 점검 이력 조회");
        System.out.println();
        System.out.println("2️⃣ 장비 번호로 조회");
        System.out.println();
        System.out.println("3️⃣ 점검 결과별 조회");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 이력 조회 메뉴
    public void repairHistoryMenu() {
        System.out.println();
        HospitalBannerUtils.printRepairBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 모든 수리 이력 조회");
        System.out.println();
        System.out.println("2️⃣ 장비 번호로 조회");
        System.out.println();
        System.out.println("3️⃣ 이력 유형별 조회");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 폐기 이력 조회 메뉴
    public void disposalHistoryMenu() {
        System.out.println();
        HospitalBannerUtils.printDisposalBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 모든 폐기 이력 조회");
        System.out.println();
        System.out.println("2️⃣ 장비 번호로 조회");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 이력 유형 선택 메뉴
    public void getHistoryTypeMenu() {
        System.out.println();
        HospitalBannerUtils.printTypeBanner();
        System.out.println("1️⃣ 점검완료");
        System.out.println();
        System.out.println("2️⃣ 수리완료");
        System.out.println();
        System.out.println("3️⃣ 폐기");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 점검 결과 유형 선택 메뉴
    public void getInspectionResultTypeMenu() {
        System.out.println();
        HospitalBannerUtils.printResultBanner();
        System.out.println("1️⃣ 양호");
        System.out.println();
        System.out.println("2️⃣ 조치필요");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 유형 선택 메뉴
    public void getRepairResultTypeMenu() {
        System.out.println();
        HospitalBannerUtils.printResultBanner();
        System.out.println("1️⃣ 수리완료");
        System.out.println();
        System.out.println("2️⃣ 수리불가");
        System.out.println();
        System.out.print("⏩ ");
    }

}
