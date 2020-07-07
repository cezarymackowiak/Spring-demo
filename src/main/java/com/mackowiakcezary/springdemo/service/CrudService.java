package com.mackowiakcezary.springdemo.service;

import com.mackowiakcezary.springdemo.model.Person;
import com.mackowiakcezary.springdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CrudService {

    private PersonRepository personRepository;

    @Autowired
    public CrudService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public void create(String name, String surname, int age) {
        Person person1 = new Person();

        person1.setAge(age);
        person1.setName(name);
        person1.setSurname(surname);
        personRepository.save(person1);
    }

    public Person read(long id) {
        final Person person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brak osoby o podanym ID"));
        return person;
    }

    public void update(long id, String name, String surname, int age) {
        final Person person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brak osoby o podanym ID"));
        if (name.equals(null) || name.isEmpty()) {
            name = person.getName();
        }
        if (age == 0) {
            age = person.getAge();
        }
        if (surname.isEmpty() || surname.equals(null)) {
            surname = person.getSurname();
        }
        person.setAge(Integer.valueOf(age));
        person.setSurname(surname);
        person.setName(name);
        personRepository.save(person);
    }

    public void delete(long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Brak osoby o podanym ID");
        }

    }

    public ArrayList<Person> readAll() {
        ArrayList<Person> array = (ArrayList<Person>) personRepository.findAll();
        return array;
    }
}
