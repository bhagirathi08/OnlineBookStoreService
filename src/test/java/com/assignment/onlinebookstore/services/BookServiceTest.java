package com.assignment.onlinebookstore.services;

import com.assignment.onlinebookstore.*;
import com.assignment.onlinebookstore.entities.*;
import com.assignment.onlinebookstore.repositories.*;
import org.hibernate.jdbc.*;
import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.rules.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;
import org.springframework.test.context.event.annotation.*;
import org.springframework.test.context.junit4.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class BookServiceTest {

    Book book1;
    Book book2;
    Book book3;

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    public void init() {
        book1 = new Book(1L,"maths","maths_author","maths_isbn",110.0,10);
        book2 = new Book(2L,"phy","phy_author","phy_isbn",120.0,20);
        book3 = new Book(3L,"chem","chem_author","chem_isbn",130.0,30);
    }


    @Test
    void addBooks() {

        //Add books
        bookService.addBooks(Arrays.asList(book1));

        //check if persisted
        Book book = bookRepository.findByIsbn(book1.getIsbn());
        Assertions.assertTrue(book1.equals(book));

        //delete testing data
        bookRepository.deleteById(book.getId());
    }

    @Test
    void addDuplicateIsbn() {

        //first copy
        bookService.addBooks(Arrays.asList(book1));

        //second copy
        Map<String,String> response = bookService.addBooks(Arrays.asList(book1));

        Assertions.assertEquals(true,response.get(book1.getIsbn()).contains("already exists"));

        //check if persisted
        Book book = bookRepository.findByIsbn(book1.getIsbn());

        //delete testing data
        bookRepository.deleteById(book.getId());
    }
}