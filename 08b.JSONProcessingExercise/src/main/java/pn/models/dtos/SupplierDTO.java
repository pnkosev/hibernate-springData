package pn.models.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SupplierDTO {

    @NotNull(message = "Supplier's name cannot be null!")
    @Length(min = 3, max = 15, message = "Supplier's name must be between 3 and 15 characters!")
    private String name;

    @NotNull(message = "Supplier's importer information is obligatory!")
    private boolean isImporter;
}
