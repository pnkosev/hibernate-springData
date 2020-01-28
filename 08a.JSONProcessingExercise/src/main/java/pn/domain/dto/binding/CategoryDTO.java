package pn.domain.dto.binding;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
    @Length(min = 3, max = 15)
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
