package pn.utils.impl;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapper {
    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
    }

    @Bean
    public ModelMapper mapper() {
        return modelMapper;
    }
}
