package pn.config;

import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pn.models.dtos.EmployeeWithManagerDto;
import pn.models.entities.Employee;

@Configuration
public class MappingConfiguration {
    static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Employee.class, EmployeeWithManagerDto.class)
                .addMapping(
                        src -> src.getManager().getLastName(),
                        EmployeeWithManagerDto::setManagerName
                );

        modelMapper.validate();
    }

    @Bean
    public ModelMapper mapper() {
        return modelMapper;
    }
}
