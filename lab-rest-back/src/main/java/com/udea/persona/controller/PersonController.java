package com.udea.persona.controller;

import com.udea.persona.model.Persona;
import com.udea.persona.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import com.udea.persona.exception.ModelNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/persona")
@CrossOrigin("*")
@Api(value = "Sistema de Personal", description = "Operaciones RESTful para gestionar empleados")
public class PersonController {

    DateFormat dform = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    Date obj = new Date();

    @Autowired
    PersonService personService;

    @ApiOperation(value = "Add Persona")
    @PostMapping("/save")
    public long save(@ApiParam(value = "Empleado guardado en la BD", required = true) @RequestBody Persona person) {
        personService.save(person);
        return person.getIdPerson();

    }

    @ApiOperation(value = "View a list of available persons", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list")
        ,
        @ApiResponse(code = 401, message = "You are not authorized to view the resource")
        ,
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
        ,
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/listAll")
    public Iterable<Persona> listAllPersons() {
        return personService.list();
    }

    @ApiOperation(value = "Get a person by Id")
    @GetMapping("/list/{id}")
    public Persona listPersonById(@ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable("id") int id) {
        Optional<Persona> person = personService.listId(id);
        if (person.isPresent()) {
            return person.get();
        }
        throw new ModelNotFoundException("ID de persona invalido");
    }

    @ApiOperation(value = "Delete a person by Id")
    @DeleteMapping("/delete/{id}")
    public void deletePersonById(@ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable("id") int id) {
        Optional<Persona> person = personService.listId(id);
        if (person.isPresent()) {
            personService.delete(person.get());
        } else {
            throw new ModelNotFoundException("ID de persona invalido");
        }
    }

    @ApiOperation(value = "Update a person by Id")
    @PutMapping("/update/{id}")
    public Persona updatePersonById(@ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable("id") int id, @RequestBody Persona person) {
        Optional<Persona> person2 = personService.listId(id);
        if (person2.isPresent()) {
            person2.get().setAddress(person.getAddress());
            person2.get().setCharge(person.getCharge());
            person2.get().setDependency(person.getDependency());
            person2.get().setEmail(person.getEmail());
            person2.get().setFirstName(person.getFirstName());
            person2.get().setLastName(person.getLastName());
            person2.get().setOffice(person.getOffice());
            person2.get().setSalary(person.getSalary());
            person2.get().setHiringDate(person.getHiringDate());

            return personService.update(person2.get());

        } else {
            throw new ModelNotFoundException("ID de persona invalido");
        }

    }

}
