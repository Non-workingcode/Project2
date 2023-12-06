package by.smirnou.project1.util;


import by.smirnou.project1.Services.PeopleServices;
import by.smirnou.project1.models.Person;
import by.smirnou.project1.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator {

    private final PeopleServices peopleServices;

    @Autowired
    public PersonValidator(PeopleServices peopleServices) {
        this.peopleServices = peopleServices;
    }

//    @Autowired
//    public PersonValidator(PersonDAO personDAO) {
//        this.personDAO = personDAO;
//    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (peopleServices.getPersonByFIO(person.getFIO()).isPresent()) {
            errors.rejectValue("fullName", "", "Человек с таким ФИО уже существует");
        }
    }
}

