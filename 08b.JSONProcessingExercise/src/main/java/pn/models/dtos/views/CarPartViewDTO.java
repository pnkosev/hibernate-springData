package pn.models.dtos.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarPartViewDTO implements Serializable {
    private CarViewNoIdDTO car;
    private List<PartViewDTO> part;
}
