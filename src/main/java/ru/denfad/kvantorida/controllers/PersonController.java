package ru.denfad.kvantorida.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.denfad.kvantorida.models.Person;
import ru.denfad.kvantorida.services.PersonService;

import java.util.List;

@RestController
@RequestMapping(path = "/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(path = "/auth", produces = "application/json")
    public Person authPerson(@RequestParam String login, @RequestParam String password){
        return personService.checkPerson(login,password);
    }

    @GetMapping(path = "/", produces = "application/json")
    public List<Person> getAllPersons(){
        return personService.getAllPerson();
    }

    @PostMapping(path = "/registration", consumes = "application/json",produces =  "application/json")
    public Person registPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @PostMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public Person updatePerson(@RequestBody Person person){
        return personService.updatePerson(person);
    }

    @GetMapping(path = "/add_place", produces = "application/json")
    public Person addPlace(@RequestParam int personId, @RequestParam int placeId){
        return personService.addPlace(personId,placeId);
    }

}
