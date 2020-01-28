package pn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pn.domain.dto.PersonDto;
import pn.service.PersonService;
import pn.utils.JsonParser;

@Controller
public class AppController implements CommandLineRunner {

    private final PersonService personService;
    private final JsonParser jsonParser;

    @Autowired
    public AppController(PersonService personService, JsonParser jsonParser) {
        this.personService = personService;
        this.jsonParser = jsonParser;
    }

    @Override
    public void run(String... args) throws Exception {
//        PersonDto[] personDtos = this.jsonParser.getFromFile(PersonDto[].class, "json/person.json");
//
//        for (PersonDto personDto : personDtos) {
//            this.personService.create(personDto);
//        }

        PersonDto personDto = this.personService.getPersonDtoFromId(1);
        this.jsonParser.writeToFile(personDto, "src/main/resources/json/person.json"); // target/classes/json/output/person.json
        System.out.println(this.jsonParser.toJson(personDto));
    }
}
