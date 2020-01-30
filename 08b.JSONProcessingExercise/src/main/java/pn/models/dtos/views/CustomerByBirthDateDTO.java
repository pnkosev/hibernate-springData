package pn.models.dtos.views;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CustomerByBirthDateDTO implements Serializable {
    @SerializedName("Id")
    private int id;

    @SerializedName("Name")
    private String name;

    @SerializedName("BirthDate")
    private Date birthDate;

    @SerializedName("IsYoungDriver")
    private boolean isYoungDriver;

    @SerializedName("Sales")
    private Set<SaleViewDTO> purchases;
}
