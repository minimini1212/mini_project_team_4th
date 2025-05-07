package equipmentAsset.repair.view;

import common.view.HospitalBannerUtils;

import java.sql.Date;
import java.sql.ResultSet;

public class RepairView {

    /** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 사용 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

    // 최상위 수리 관리 메뉴
    public void repairMenu() {
        System.out.println();
        HospitalBannerUtils.printRepairBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 수리 요청 관리");
        System.out.println();
        System.out.println("2️⃣ 수리 결과 관리");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 요청 관리 메뉴
    public void repairRequestMenu() {
        System.out.println();
        HospitalBannerUtils.printRequestBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 수리 요청 등록");
        System.out.println();
        System.out.println("2️⃣ 수리 요청 조회");
        System.out.println();
        System.out.println("3️⃣ 수리 요청 수정");
        System.out.println();
        System.out.println("4️⃣ 수리 요청 삭제");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 요청 등록 메뉴
    public void saveRepairRequestMenu() {
        System.out.println();
        HospitalBannerUtils.printRegisterBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 수리 요청 등록");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 요청 조회 메뉴
    public void findRepairRequestMenu() {
        System.out.println();
        HospitalBannerUtils.printSearchBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 모든 수리 요청 조회");
        System.out.println();
        System.out.println("2️⃣ 장비 번호로 조회");
        System.out.println();
        System.out.println("3️⃣ 요청 번호로 조회");
        System.out.println();
        System.out.println("4️⃣ 상태별 조회");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 요청 수정 메뉴
    public void updateRepairRequestMenu() {
        System.out.println();
        HospitalBannerUtils.printUpdateBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 수리 요청 수정");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 요청 삭제 메뉴
    public void deleteRepairRequestMenu() {
        System.out.println();
        HospitalBannerUtils.printDeleteBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 수리 요청 삭제");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 관리 메뉴
    public void repairResultMenu() {
        System.out.println();
        HospitalBannerUtils.printResultsBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 수리 결과 등록");
        System.out.println();
        System.out.println("2️⃣ 수리 결과 조회");
        System.out.println();
        System.out.println("3️⃣ 수리 결과 수정");
        System.out.println();
        System.out.println("4️⃣ 수리 결과 삭제");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 등록 메뉴
    public void saveRepairResultMenu() {
        System.out.println();
        HospitalBannerUtils.printRegisterBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 수리 결과 등록");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 조회 메뉴
    public void findRepairResultMenu() {
        System.out.println();
        HospitalBannerUtils.printSearchBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 모든 수리 결과 조회");
        System.out.println();
        System.out.println("2️⃣ 장비 번호로 조회");
        System.out.println();
        System.out.println("3️⃣ 요청 번호로 조회");
        System.out.println();
        System.out.println("4️⃣ 결과 유형별 조회");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 수정 메뉴
    public void updateRepairResultMenu() {
        System.out.println();
        HospitalBannerUtils.printUpdateBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 수리 결과 수정");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 삭제 메뉴
    public void deleteRepairResultMenu() {
        System.out.println();
        HospitalBannerUtils.printDeleteBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 수리 결과 삭제");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 요청 수정 항목 메뉴
    public void updateRepairRequestItemMenu() {
        System.out.println();
        HospitalBannerUtils.printUpdateBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 요청 일자 수정");
        System.out.println();
        System.out.println("2️⃣ 고장 증상 수정");
        System.out.println();
        System.out.println("3️⃣ 요청 상태 수정");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 수정 항목 메뉴
    public void updateRepairResultItemMenu() {
        System.out.println();
        HospitalBannerUtils.printUpdateBanner();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println();
        System.out.println("1️⃣ 수리 내용 수정");
        System.out.println();
        System.out.println("2️⃣ 수리 비용 수정");
        System.out.println();
        System.out.println("3️⃣ 결과 유형 수정");
        System.out.println();
        System.out.print("⏩ ");
    }

    /** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 안내 출력 =-=-=-=-=-=-=-=-=-=-=-= **/

    // 수리 요청 상태 선택 출력
    public void getRepairRequestStatus() {
        System.out.println();
        HospitalBannerUtils.printStatusBanner();
        System.out.println("1️⃣ 예정");
        System.out.println();
        System.out.println("2️⃣ 완료");
        System.out.println();
        System.out.print("⏩ ");
    }

    // 수리 결과 유형 선택 출력
    public void getRepairResultType() {
        System.out.println();
        HospitalBannerUtils.printResultsBanner();
        System.out.println("1️⃣ 수리완료");
        System.out.println();
        System.out.println("2️⃣ 수리불가");
        System.out.println();
        System.out.print("⏩ ");
    }
}