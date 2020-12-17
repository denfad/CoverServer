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
    private final PlaceService placeService;

    @Autowired
    public PersonService(PersonRepository personRepository, PlaceService placeService) {
        this.personRepository = personRepository;
        this.placeService = placeService;
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

    public Person updatePerson(Person person){
        return  personRepository.save(person);
    }

    public Person getPersonById(int id){
        return personRepository.getOne(id);
    }

    public Person addPlace(int personId, int placeId){
        Person p = getPersonById(personId);
        p.addPlace(placeService.getPlaceById(placeId));
        personRepository.save(p);
        return p;
    }

    public int getIllPersons(String status){
        return personRepository.getIllPersons(status);
    }
}
