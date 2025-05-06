package dbConn;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbConn.*;
import lombok.Getter;

@Getter
public abstract class BaseDAO {
    // 공통 DB 연결 관련 객체
    protected Statement stmt = null;
    protected PreparedStatement pstmt = null;
    protected ResultSet rs = null;
    protected Connection conn = null;
    protected CallableStatement cstmt = null;

    // connect - 모든 DAO 클래스가 공통으로 사용
    public void connect() {
        try {
            conn = ConnectionSingletonHelper.getConnection("oracle");
            stmt = conn.createStatement();
            conn.setAutoCommit(false); // 자동커밋 끄기
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // close - 모든 DAO 클래스가 공통으로 사용
    public void close() {
        try {
            CloseHelper.close(rs);
            CloseHelper.close(stmt);
            CloseHelper.close(pstmt);
            CloseHelper.close(cstmt);
            CloseHelper.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
