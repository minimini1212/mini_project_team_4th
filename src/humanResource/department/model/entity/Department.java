package humanResource.department.model.entity;

import common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseEntity {
    private int departmentId;
    private String departmentName;
    private String departmentCode;
    private int managerId;
    private String description;
}
