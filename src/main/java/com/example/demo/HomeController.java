package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        bookRepository.save(book);
        return "redirect:/add";
    }

    @RequestMapping("/borrowed")
    public String showBorrowed(Model model){
        model.addAttribute("books", bookRepository.findAll());

        return "borrowedList";
    }

    @PostMapping("/confBorrowed")
    public String confBorrowed(@Valid @ModelAttribute("book") Book book, BindingResult result){
        book.setIsBorrowed(TRUE);
        return "ConfirmedBorrowed";
    }


    @RequestMapping("/returned")
    public String showReturned(Model model){
        model.addAttribute("books", bookRepository.findAll());

        return "returnedList";
    }

    @PostMapping("/confReturned")
    public String confReturned(){

        return "ConfirmedReturned";
    }


}
