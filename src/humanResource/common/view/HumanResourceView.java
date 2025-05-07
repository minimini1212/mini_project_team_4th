package humanResource.common.view;

import common.view.HospitalBannerUtils;

public class HumanResourceView {
    public void humanResourceMenuForMaster() {
        System.out.println();
        HospitalBannerUtils.printHrBanner();
        System.out.println();
        System.out.println("0️⃣ 이전 메뉴 돌아가기");
        System.out.println("1️⃣ 직원 관리");
        System.out.println("2️⃣ 자격증 관리");
        System.out.println("3️⃣ 휴가 관리");
        System.out.println();
        System.out.print("⏩ ");
    }

    public void humanResourceView() {
        System.out.println();
        HospitalBannerUtils.printHrBanner();
        System.out.println();
        System.out.println("0️⃣ 로그아웃");
        System.out.println("1️⃣ 직원 관리");
        System.out.println("2️⃣ 자격증 관리");
        System.out.println("3️⃣ 휴가 관리");
        System.out.println();
        System.out.print("⏩ ");
    }
}
