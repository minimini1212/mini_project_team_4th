package com.hospital.main;

import com.hospital.leave.controller.LeaveController;

public class Main {
    public static void main(String[] args) {
        // LeaveController 내부에서 while‑loop로 메뉴를 계속 보여 줍니다.
        new LeaveController().leaveMenu();
    }
}