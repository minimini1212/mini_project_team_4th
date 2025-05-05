package humanResource.userAccount.model.entity;

import common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount extends BaseEntity {
    private int accountId;
    private String userId;
    private String password;
    private int employeeId;
}
