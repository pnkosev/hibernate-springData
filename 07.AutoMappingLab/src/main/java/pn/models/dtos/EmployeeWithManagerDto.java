package pn.models.dtos;

import java.math.BigDecimal;

public class EmployeeWithManagerDto {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private String managerName;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getManagerName() {
        return this.managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f - Manager: %s",
                this.firstName,
                this.lastName,
                this.salary,
                this.managerName != null ? this.managerName : "[no manager]");
    }
}
