package common.view;


public class HospitalBannerUtils {

    // ANSI 색상 코드 상수
    private static final String BLUE = "\033[1;34m";
    private static final String WHITE = "\033[1;97m";
    private static final String RESET = "\033[0m";

    // 구분선 문자열
    private static final String DIVIDER = "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬";

    public static void printHospitalLogo() {
        // 상단 구분선 - 파란색
        System.out.println(BLUE + DIVIDER + RESET);

        // HOSP 로고 - 파란색과 밝은 하얀색 교차
        System.out.println(
                BLUE + "        ┏┓︱┏┓ ┏━━┓ ┏━━━┓\n" + RESET +
                        WHITE + "        ┃┃︱┃┃ ┗┫┣┛ ┃┏━┓┃\n" + RESET +
                        BLUE + "        ┃┗━┛┃ ︱┃┃︱ ┃┗━━┓\n" + RESET +
                        WHITE + "        ┃┏━┓┃ ︱┃┃︱ ┗━━┓┃\n" + RESET +
                        BLUE + "        ┃┃︱┃┃ ┏┫┣┓ ┃┗━┛┃\n" + RESET +
                        WHITE + "        ┗┛︱┗┛ ┗━━┛ ┗━━━┛" + RESET);

        // 중간 구분선 - 파란색
        System.out.println(BLUE + DIVIDER + RESET);

        // 시스템 버전 정보 - 파란색 테두리, 흰색 텍스트
        System.out.println(
                BLUE + "┏━━━━━━━━━━━━━━━━━━━━━┓\n" + RESET +
                        BLUE + "┃" + WHITE + "           Hospital System V 1.0          " + BLUE + "┃\n" + RESET +
                        BLUE + "┗━━━━━━━━━━━━━━━━━━━━━┛" + RESET);
    }

    /**
     * 메뉴 배너를 출력합니다.
     * @param title 배너에 표시할 제목
     */
    public static void printMenuBanner(String title) {
        // 특수문자와 공백을 조정하여 일관된 형태로 출력
        String formattedTitle = formatTitle(title);

        System.out.println(BLUE +
                "  ▌│█║▌║▌   " + WHITE + formattedTitle +
                BLUE + "   ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }


    public static void printLongMenuBanner(String title) {
        // 특수문자와 공백을 조정하여 일관된 형태로 출력
        String formattedTitle = formatTitle(title);

        System.out.println(BLUE +
                "  ▌│█║▌║▌  " + WHITE + formattedTitle +
                BLUE + "  ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
    }

    // 폰트 관련 메소드
    private static String formatTitle(String title) {
        return title;
    }

    // 미리 정의된 메뉴 배너 메서드들

    public static void printLoginBanner() {
        printMenuBanner("𝙇 𝙊 𝙂 𝙄 𝙉");
    }

    public static void printRegisterBanner() {
        printMenuBanner("𝙍 𝙀 𝙂 𝙄 𝙎 𝙏");
    }

    public static void printSelectBanner() {
        printMenuBanner("𝙎 𝙀 𝙇 𝙀 𝘾 𝙏");
    }

    public static void printSearchBanner() {
        printMenuBanner("𝙎 𝙀 𝘼 𝙍 𝘾 𝙃");
    }

    public static void printUpdateBanner() {
        printMenuBanner("𝙐 𝙋 𝘿 𝘼 𝙏 𝙀");
    }

    public static void printDeleteBanner() {
        printMenuBanner("𝘿 𝙀 𝙇 𝙀 𝙏 𝙀");
    }

    public static void printStatusBanner() {
        printMenuBanner("𝙎 𝙏 𝘼 𝙏 𝙐 𝙎");
    }

    public static void printHrBanner() {
        printMenuBanner("𝙃   𝙍");
    }

    public static void printEmployeeBanner() {
        printLongMenuBanner("𝙀 𝙈 𝙋 𝙇 𝙊 𝙔 𝙀 𝙀");
    }

    public static void printResultsBanner() {
        printLongMenuBanner("𝙍 𝙀 𝙎 𝙐 𝙇 𝙏 𝙎");
    }

    public static void printRequestBanner() {
        printLongMenuBanner("𝙍 𝙀 𝙌 𝙐 𝙀 𝙎 𝙏");
    }

    public static void printRepairBanner() {
        printMenuBanner("𝙍 𝙀 𝙋 𝘼 𝙄 𝙍");
    }


    public static void printTypeBanner() {
        System.out.println(BLUE +
                "  ▌│█║▌║▌     " + WHITE + "𝙏 𝙔 𝙋 𝙀" +
                BLUE + "     ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    public static void printCycleBanner() {
        System.out.println(BLUE +
                "  ▌│█║▌║▌    " + WHITE + "𝘾 𝙔 𝘾 𝙇 𝙀" +
                BLUE + "    ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    public static void printScheduleBanner() {
        System.out.println(BLUE +
                "  ▌│█║▌    " + WHITE + "𝙎 𝘾 𝙃 𝙀 𝘿 𝙐 𝙇 𝙀" +
                BLUE + "     ▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    public static void printInspectionBanner() {
        System.out.println(BLUE +
                "  ▌│█║▌   " + WHITE + "𝙄 𝙉 𝙎 𝙋 𝙀 𝘾 𝙏 𝙄 𝙊 𝙉" +
                BLUE + "  ▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }


    // 이력 관리 메인 메뉴 배너
    public static void printHistoryBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌  " + WHITE + "𝙃 𝙄 𝙎 𝙏 𝙊 𝙍 𝙔" +
                BLUE + "  ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 통합 이력 배너
    public static void printIntegratedBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌  " + WHITE + "𝙄 𝙉 𝙏 𝙀 𝙂 𝙍 𝘼 𝙏 𝙀 𝘿" +
                BLUE + "   ▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }


    // 폐기 배너
    public static void printDisposalBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌ " + WHITE + "𝘿 𝙄 𝙎 𝙋 𝙊 𝙎 𝘼 𝙇" +
                BLUE + " ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 결과 배너
    public static void printResultBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌   " + WHITE + "𝙍 𝙀 𝙎 𝙐 𝙇 𝙏" +
                BLUE + "   ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 장비 관리 메인 메뉴 배너
    public static void printManageBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌  " + WHITE + "𝙈 𝘼 𝙉 𝘼 𝙂 𝙀" +
                BLUE + "  ▌║▌║█│ " + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 입력 배너
    public static void printInputBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌    " + WHITE + "𝙄 𝙉 𝙋 𝙐 𝙏" +
                BLUE + "    ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 대시보드 배너
    public static void printDashboardBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌   " + WHITE + "𝘿𝘼𝙎𝙃𝘽𝙊𝘼𝙍𝘿" +
                BLUE + "   ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 카테고리 관리 배너
    public static void printCategoryBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌ " + WHITE + "𝘾 𝘼 𝙏 𝙀 𝙂 𝙊 𝙍 𝙔" +
                BLUE + " ▌║▌║█│" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 부서 입력 배너
    public static void printDeptBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌     " + WHITE + "𝘿 𝙀 𝙋 𝙏" +
                BLUE + "     ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 장비 배너
    public static void printEquipmentBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌   " + WHITE + "𝙀 𝙌 𝙐 𝙄 𝙋 𝙈 𝙀 𝙉 𝙏" +
                BLUE + "   ▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 휴가 배너
    public static void printVacationBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌    " + WHITE + "𝙑𝘼𝘾𝘼𝙏𝙄𝙊𝙉" +
                BLUE + "    ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 증명서 배너
    public static void printCertificateBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌   " + WHITE + "𝘾𝙀𝙍𝙏𝙄𝙁𝙄𝘾𝘼𝙏𝙀" +
                BLUE + "   ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 직원 증명서 배너
    public static void printEmpCertificateBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌ " + WHITE + "𝙀𝙈𝙋 𝘾𝙀𝙍𝙏𝙄𝙁𝙄𝘾𝘼𝙏𝙀" +
                BLUE + " ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 예산 배너
    public static void printBudgetBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌   " + WHITE + "𝘽 𝙐 𝘿 𝙂 𝙀 𝙏" +
                BLUE + "   ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 예산 요청 배너
    public static void printBudgetRequestBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌ " + WHITE + "𝘽𝙐𝘿𝙂𝙀𝙏 𝙍𝙀𝙌𝙐𝙀𝙎𝙏" +
                BLUE + " ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 회계 배너
    public static void printAccountingBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌   " + WHITE + "𝘼𝘾𝘾𝙊𝙐𝙉𝙏𝙄𝙉𝙂" +
                BLUE + "   ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 지출 배너
    public static void printExpenseBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║    " + WHITE + "𝙀 𝙓 𝙋 𝙀 𝙉 𝙎 𝙀" +
                BLUE + "    ║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 지출 요청 배너
    public static void printExpenseRequestBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║  " + WHITE + "𝙀𝙓𝙋𝙀𝙉𝙎𝙀 𝙍𝙀𝙌𝙐𝙀𝙎𝙏" +
                BLUE + "  ║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

    // 통계 배너
    public static void printStatisticsBanner() {
        System.out.println();
        System.out.println(BLUE +
                "  ▌│█║▌║▌    " + WHITE + "𝙎𝙏𝘼𝙏𝙄𝙎𝙏𝙄𝘾𝙎" +
                BLUE + "    ▌║▌║█│▌" + RESET);

        System.out.println(BLUE + DIVIDER + RESET);
        System.out.println();
    }

}
