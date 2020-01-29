package pn.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationConfiguration {
    private static ModelMapper modelMapper;
    private static Validator validator;

    static {
        modelMapper = new ModelMapper();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ModelMapper mapper() {
        return modelMapper;
    }

    @Bean
    public Validator validator() {
        return validator;
    }
}
