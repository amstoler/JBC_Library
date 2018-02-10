package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

import static java.lang.Boolean.TRUE;

@Controller
public class HomeController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/")
    public String homepage() {

        return "home";
    }

    @RequestMapping("/bookList")
    public String listBooks(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "bookList";
    }

    @GetMapping("/add")
    public String bookForm(Model model){
        model.addAttribute("book", new Book());
        return "bookForm";
    }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("book") Book book, BindingResult result)
    {
        if(result.hasErrors()){
            return "bookForm";
        }
        if(book.getImage().isEmpty()){
            book.setImage("https://openclipart.org/download/276068/Open-Book-Remixed.svg");
        }
        bookRepository.save(book);
        return "redirect:/add";
    }



    @RequestMapping("/borrow")
    public String showBorrowed(Model model){
        model.addAttribute("availablebooks", bookRepository.findAllByIsBorrowed(false));
        return "borrowedList";
    }

    @RequestMapping("/confBorrowed/{id}")
    public String confBorrowed(@PathVariable("id") long id, Model model){

        Book oldBook = bookRepository.findOne(new Long(id));
        oldBook.setIsBorrowed(true);
        bookRepository.save(oldBook);

        /*model.addAttribute("book", bookRepository.findOne(id));*/


        return "ConfirmedBorrowed";
    }

    @RequestMapping("/return")
    public String showReturned(Model model){
        model.addAttribute("reservedbooks", bookRepository.findAllByIsBorrowed(true));

        return "returnedList";
    }

    @RequestMapping("/confReturned/{id}")
    public String confReturned(@PathVariable("id") long id, Model model){

        Book oldBook = bookRepository.findOne(new Long(id));
        oldBook.setIsBorrowed(false);
        bookRepository.save(oldBook);

        /*model.addAttribute("book", bookRepository.findOne(id));*/


        return "ConfirmedReturned";
    }

    @PostMapping("/confReturned")
    public String confReturned(){

        return "ConfirmedReturned";
    }

    /*@RequestMapping("/detail/{id}")
    public String showStatus(@PathVariable("id") long id, Model model){
        model.addAttribute("book"), bookRepository.findOne(id);
        return
    }*/



}
