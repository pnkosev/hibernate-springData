package pn.service.impl;

import pn.domain.dto.PersonDto;
import pn.domain.model.Person;

import java.util.List;

public interface PersonService {
    void create(Person person);

    void create(PersonDto personDto);

    Person findById(long id);

    PersonDto getPersonDtoFromId(long id);

    List<Person> findByCountry(String country);
}
