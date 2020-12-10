package ru.denfad.kvantorida.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.denfad.kvantorida.models.Person;
import ru.denfad.kvantorida.repository.PersonRepository;

import java.util.List;

@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person addPerson(Person p){
        return personRepository.save(p);
    }

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    public Person checkPerson(String login, String password){
        return personRepository.findUserByLoginAndPassword(login,password);
    }
}
