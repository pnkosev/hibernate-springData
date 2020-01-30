package pn.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    static {
        mapper = new ModelMapper();
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
                .create();;
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ModelMapper mapper() {
        return mapper;
    }

    @Bean
    public Gson gson() { return gson; }

    @Bean
    public Validator validator() { return validator; }
}
