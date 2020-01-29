package pn.config;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    private static ModelMapper mapper;
    private static Gson gson;

    static {
        mapper = new ModelMapper();
        gson = new Gson();
    }

    @Bean
    public ModelMapper mapper() {
        return mapper;
    }

    @Bean
    public Gson gson() {
        return gson;
    }
}
