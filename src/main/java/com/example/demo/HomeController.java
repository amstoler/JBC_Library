package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/")
    public String sampletext() {

        return "home";
    }
}
