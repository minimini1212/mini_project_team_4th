package common;

import java.sql.Timestamp;

public abstract class BaseEntity {
    protected String delYn = "N"; // 기본값 N
//    protected Timestamp createdAt;
//    protected Timestamp updatedAt;

    public String getDelYn() { return delYn; }
    public void setDelYn(String delYn) { this.delYn = delYn; }

//    public Timestamp getCreatedAt() { return createdAt; }
//    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
//
//    public Timestamp getUpdatedAt() { return updatedAt; }
//    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}
