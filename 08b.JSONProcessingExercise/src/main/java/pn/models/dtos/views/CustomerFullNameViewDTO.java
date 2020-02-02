package pn.models.dtos.views;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFullNameViewDTO implements Serializable {
    @SerializedName("customerName")
    private String fullName;
}
