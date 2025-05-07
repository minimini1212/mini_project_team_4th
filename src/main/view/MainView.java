package main.view;

import common.view.HospitalBannerUtils;


public class MainView {
    public void showDepartmentMenu() {
        HospitalBannerUtils.printSelectBanner();
        System.out.println();
        System.out.println("0️⃣ 로그아웃");
        System.out.println("1️⃣ 인사 관리 부서");
        System.out.println("2️⃣ 예산/회계 관리 부서");
        System.out.println("3️⃣ 자산 관리 부서");
        System.out.println();
    }
}
