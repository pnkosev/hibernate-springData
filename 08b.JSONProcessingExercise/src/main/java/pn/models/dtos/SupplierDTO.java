package pn.models.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class SupplierDTO implements Serializable {

    @NotNull(message = "Supplier's name cannot be null!")
    @Length(min = 3, message = "Supplier's name must be at least 3 characters!")
    private String name;

    @NotNull(message = "Supplier's importer information is obligatory!")
    private boolean isImporter;
}
