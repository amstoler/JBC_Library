package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface BookRepository extends CrudRepository<Book,Long> {
    ArrayList<Book> findBooksByBorrowedIsFalse = new ArrayList<>();
}
