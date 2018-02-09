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

    @RequestMapping("/borrow")
    public String showBorrowed(){

        return "borrowedList";
    }

    @RequestMapping("/return")
    public String showReturned(){

        return "returnedList";
    }


}
