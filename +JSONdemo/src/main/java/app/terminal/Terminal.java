package app.terminal;

import app.domain.dto.PersonDto;
import app.io.JSONParser;
import app.service.PersonService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class Terminal implements CommandLineRunner {
    private PersonService personService;
    private JSONParser parser;

    @Autowired
    public Terminal(PersonService personService) {
        this.personService = personService;
        this.parser = new JSONParser();
    }

    @Override
    public void run(String... strings) throws Exception {
        PersonDto dto = this.personService.findById(1);
        this.parser.outputJson(dto, "src/main/resources/files/persons.json");
    }
}
