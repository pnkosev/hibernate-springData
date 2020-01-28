package pn.domain.dto.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

public class UserDTO implements Serializable {
    @NotNull
    private String firstName;

    @Length(min = 3)
    @NotNull
    private String lastName;

    @Min(3)
    private int age;

    public UserDTO() {
    }

    public UserDTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

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

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
