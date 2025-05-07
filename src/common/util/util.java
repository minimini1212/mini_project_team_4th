package common.util;

import java.util.Scanner;

public class util {

    // 문자 시간차 출력 delayMillis << 이걸 수정하면 출력 간격 바꿀 수 있음.
    public void printfWithDelay(String format, Object... args) {
        int delayMillis = 15;
        String formattedString = String.format(format, args);

        for (char c : formattedString.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
