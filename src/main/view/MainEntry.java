package main.view;

import common.SessionContext;
import dbConn.ConnectionSingletonHelper;
import humanResource.userAccount.controller.UserAccountController;
import humanResource.userAccount.model.service.UserAccountService;
import main.controller.MainController;

import java.sql.Connection;
import java.util.Scanner;

// 최상위 View
public class MainEntry {
	public static void main(String[] args) {
		try {

			// DAO/Service 객체 생성
			UserAccountService userAccountService = new UserAccountService();
			UserAccountController userAccountController = new UserAccountController();

			// 로그인 메뉴 호출
			userAccountController.loginMenu();

			// 세션 정보 확인 후 MainController 실행
			if (SessionContext.isLoggedIn()) {
				MainController mainController = new MainController(new Scanner(System.in));
				mainController.run();
			} else {
				System.out.println("로그인 후 진행해 주세요.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}