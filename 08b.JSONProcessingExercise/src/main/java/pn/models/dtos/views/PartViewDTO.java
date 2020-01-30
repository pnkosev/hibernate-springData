package pn.models.dtos.views;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartViewDTO implements Serializable {
    @SerializedName("Name")
    private String name;

    @SerializedName("Price")
    private BigDecimal price;
}
