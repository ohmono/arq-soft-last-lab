package com.udea.persona.service;

import com.udea.persona.dao.IPersonDAO;
import com.udea.persona.model.Persona;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private IPersonDAO dao;

    public Persona save(Persona t) {
        return dao.save(t);
    }

    public Persona update(Persona t) {
        return dao.save(t);
    }

    public void delete(Persona t) {
        dao.delete(t);
    }

    public Iterable<Persona> list() {
        return dao.findAll();
    }

    public Optional<Persona> listId(long id) {
        return dao.findById(id);
    }

    // agregar método para realizar un schedule job para actualizar lso salarios diariamente
    // cron expresion repeat Every day at 12:00 AM
    @Scheduled(cron = "0/15 * * * * *")
    public void increaseSalary() throws ParseException {
        //
        Iterable<Persona> people = dao.findAll();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateToday = formatter.parse(formatter.format(date));

        for (Persona person : people) {
            String hiringDate = formatter.format(person.getHiringDate());
            Date dateHiring = formatter.parse(hiringDate);

            long diffInMillies = Math.abs(dateToday.getTime() - dateHiring.getTime());
            long difference = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            if (Math.floor(difference / 365) > 2) {
                double amount = Math.floor(difference / 365 * 2) * 1.2 * person.getSalary();
                person.setSalary(amount);
                person.setHiringDate(date);
                dao.save(person);
            }
            System.out.println(person.getHiringDate());
            System.out.println(person.getSalary());
        }

    }
}
