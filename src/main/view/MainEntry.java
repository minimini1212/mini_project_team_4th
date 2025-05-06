package main.view;

import common.SessionContext;
import humanResource.userAccount.controller.UserAccountController;
import main.controller.MainController;

import java.util.Scanner;

// 최상위 View
public class MainEntry {
	public static void main(String[] args) {
		try {

			// DAO/Service 객체 생성
			UserAccountController userAccountController = new UserAccountController();

			// 로그인 메뉴 호출
			boolean isLoginSuccess  = userAccountController.loginMenu();

			// 세션 정보 확인 후 MainController 실행
			if (isLoginSuccess && SessionContext.isLoggedIn()) {
				MainController mainController = new MainController(new Scanner(System.in));
				mainController.run();
			}


		} catch (Exception e) {
			System.out.println("❌ 프로그램 실행 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
		}
	}
}