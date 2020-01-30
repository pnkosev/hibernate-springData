package pn.config;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Random;

@Configuration
public class ApplicationConfiguration {
    private static ModelMapper mapper;
    private static Gson gson;
    private static Validator validator;
    private static Random random;

    static {
        mapper = new ModelMapper();
        gson = new Gson();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        random = new Random();
    }

    @Bean
    public ModelMapper mapper() {
        return mapper;
    }

    @Bean
    public Gson gson() { return gson; }

    @Bean
    public Validator validator() { return validator; }

    @Bean
    public Random random() { return random; }
}
