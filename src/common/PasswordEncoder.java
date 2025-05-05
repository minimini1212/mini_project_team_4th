package common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    // 비밀번호 암호화
    public static String encode(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            // byte[] → 16진수 문자열 변환
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 암호화 오류", e);
        }
    }

    // 비밀번호 비교
    public static boolean matches(String rawPassword, String hashedPassword) {
        return encode(rawPassword).equals(hashedPassword);
    }
}
