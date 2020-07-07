package com.mackowiakcezary.springdemo.controller;

import com.mackowiakcezary.springdemo.model.Person;
import com.mackowiakcezary.springdemo.service.CrudService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@Validated
public class SpringDemoApplicationController {

    private CrudService crudService;

    @Autowired
    public SpringDemoApplicationController(CrudService crudService) {
        this.crudService = crudService;
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sukces, prawidlowe wykonanie uslugi", response = ResponseStatus.class)})
    @ApiOperation(value = "Utworzenie osoby")
    public String create(@RequestParam(value = "name") String name,
                         @RequestParam(value = "surname") String surname,
                         @RequestParam(value = "age") @Valid Integer age) {
        if (age < 18)
            return "age must be over 18";
        else {
            crudService.create(name, surname, age.intValue());


            return "Person created sucesfully";
        }
    }

    @RequestMapping(path = "/read", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sukces, prawidlowe wykonanie uslugi", response = ResponseStatus.class)})
    @ApiOperation(value = "Odczyt osoby")
    public Person read(@RequestParam(value = "id") long id) {
        final Person read = crudService.read(id);
        return read;
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sukces, prawidlowe wykonanie uslugi", response = ResponseStatus.class)})
    @ApiOperation(value = "Aktualizacja danych osoby")
    public void update(@RequestParam(value = "id") long id,
                       @RequestParam(value = "name", required = false, defaultValue = "") String name,
                       @RequestParam(value = "surname", required = false, defaultValue = "") String surname,
                       @RequestParam(value = "age", required = false, defaultValue = "18") int age) {
        crudService.update(id, name, surname, Integer.valueOf(age));
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sukces, prawidlowe wykonanie uslugi", response = ResponseStatus.class)})
    @ApiOperation(value = "Usunięcie osoby")
    public void delete(@RequestParam(value = "id") long id) {
        crudService.delete(id);
    }

    @RequestMapping(path = "/readAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sukces, prawidlowe wykonanie uslugi", response = ResponseStatus.class)})
    @ApiOperation(value = "odczyt wszystkich osob")
    public ArrayList<Person> readAll() {
        final ArrayList<Person> array = crudService.readAll();
        return array;
    }
}
