package hr.employee.model.entity;

import common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity {
    private int employeeId;
    private String empNumber;
    private String name;
    private Date birthDate;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private Date hireDate;
    private Date resignDate;
    private String status;
    private String empType;

    private int departmentId;
    private int positionId;
    private int jobId;

}
