package by.smirnou.project1.controllers;

import by.smirnou.project1.Services.PeopleServices;

import by.smirnou.project1.util.PersonValidator;
import jakarta.validation.Valid;
import by.smirnou.project1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleServices peopleServices;
    private final PersonValidator personValidator;


    @Autowired
    public PeopleController(PeopleServices peopleServices , PersonValidator personValidator) {
        this.peopleServices = peopleServices;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleServices.findAll());
        return "/people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleServices.findOne(id));
        model.addAttribute("person", peopleServices.getBooksByPersonId(id));
        model.addAttribute("person", peopleServices.getBooksByPersonId(id));

        return "/people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") @Valid Person person) {
        return "/people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person ,
                         BindingResult bindingResult){
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors()){
            return "/people/new";
        }
        peopleServices.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleServices.findOne(id));
        return "/people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable ("id") int id,
                         @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "/people/edit";
        }
        peopleServices.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleServices.delete(id);
        return "redirect:/people";
    }




}
