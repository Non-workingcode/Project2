package by.smirnou.project1.controllers;


import by.smirnou.project1.Services.BooksServices;
import by.smirnou.project1.Services.PeopleServices;

import by.smirnou.project1.models.Book;
import by.smirnou.project1.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksServices booksServices;
    private final PeopleServices peopleServices;

    public BooksController(BooksServices booksServices, PeopleServices peopleServices) {
        this.booksServices = booksServices;
        this.peopleServices = peopleServices;
    }

    @GetMapping
    public String index(Model model, @RequestParam("page") String page ,
                        @RequestParam("itemsPerPage") String itemsPerPage,
                        @RequestParam("sort_by_year") boolean sort_by_year){
        if(page.isEmpty() && itemsPerPage.isEmpty()) {
            model.addAttribute("book", booksServices.findAll());
        }
        else if(sort_by_year==true){
            model.addAttribute("book", booksServices.findAll("year"));
        }
        else if(sort_by_year==false){
            model.addAttribute("book", booksServices.findAll(1,3));
        }
        else {
            model.addAttribute("book", booksServices.findAll(1,3 , "year"));
        }

        return "/books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable ("id") int id, Model model,
                       @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksServices.findOne(id));

        Optional<Person> bookOwner = booksServices.findOwner(id);

        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("people", peopleServices.findAll());
        }
        return "/book/show";
    }

    @GetMapping("/search")
    public  String search(@RequestParam("title") String title){
//        model.addAttribute("books",booksServices.findBookByTitleStartingWith(title).stream().findAny());
        booksServices.findBookByTitleStartingWith(title).stream().findAny();
        peopleServices.findPersonByTitle(title);
        return "/search";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "/books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book ,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/books/new";
        }
        booksServices.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model , @PathVariable("id") int id){
        model.addAttribute("book", booksServices.findOne(id));
        return "/books/edit";
    }



    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                          BindingResult bindingResult ,
                          @PathVariable ("id") int id){
        if(bindingResult.hasErrors()){
            return "/books/edit";
        }
        booksServices.update(book,id);
        return "redirect:/edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        booksServices.delete(id);
        return "redirect:/books";
    }


    @PatchMapping("/{id}")
    public String release(@PathVariable("id") int id){
        booksServices.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping ("/{id}")
    public String assign(@PathVariable("id") int id,
                         @ModelAttribute("person") Person selectedPerson){
        //У selectedPerson назначено только поле id, остальные поля - null
        booksServices.assign(id,selectedPerson);
        return "redirect:/books/" + id;
    }


}
