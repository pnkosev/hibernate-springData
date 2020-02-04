package car_dealer.config;

import car_dealer.util.ValidatorUtil;
import car_dealer.util.XMLParser;
import car_dealer.util.impl.ValidatorUtilImpl;
import car_dealer.util.impl.XMLParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationConfiguration {

    private static ModelMapper mapper;
    private static Validator validator;
    private static ValidatorUtil validatorUtil;
    private static XMLParser xmlParser;

    static {
        mapper = new ModelMapper();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validatorUtil = new ValidatorUtilImpl(validator);
        xmlParser = new XMLParserImpl();
    }

    @Bean
    public ModelMapper mapper() { return mapper; }

    @Bean
    public Validator validator() { return validator; }

    @Bean
    public ValidatorUtil validatorUtil() { return validatorUtil; }

    @Bean
    public XMLParser xmlParser() { return xmlParser; }
}
