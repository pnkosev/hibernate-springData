package pn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.domain.dto.PersonDto;
import pn.domain.model.Person;
import pn.repository.PersonRepository;
import pn.service.PersonService;
import pn.utils.ObjectMapper;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void create(Person person) {
        this.personRepository.saveAndFlush(person);
    }

    @Override
    public void create(PersonDto personDto) {
        Person person = getPersonFromPersonDto(personDto);
        this.personRepository.saveAndFlush(person);
    }

    private Person getPersonFromPersonDto(PersonDto personDto) {
        Person person = ObjectMapper.getInstance().map(personDto, Person.class);
        person.getPhoneNumbers().forEach(phoneNumber -> phoneNumber.setPerson(person));
        return person;
    }

    @Override
    public Person findById(long id) {
        return this.personRepository.findById(id).orElse(null);
    }

    @Override
    public PersonDto getPersonDtoFromId(long id) {
        Person person = this.findById(id);
        if (person != null) {
            return ObjectMapper.getInstance().map(person, PersonDto.class);
        }
        return null;
    }

    @Override
    public List<Person> findByCountry(String country) {
        return this.personRepository.findByCountry(country);
    }
}
