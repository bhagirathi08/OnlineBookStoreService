package com.assignment.onlinebookstore.services;

import com.assignment.onlinebookstore.entities.*;
import com.assignment.onlinebookstore.repositories.*;
import org.junit.jupiter.api.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class SearchServiceTest {

    Book book1;
    Book book2;
    Book book3;

    @Autowired
    BookService bookService;

    @Autowired
    SearchService searchService;

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    public void init() {
        book1 = new Book(1L,"maths_1","maths_1_author","maths_1_isbn",110.0,10);
        book2 = new Book(2L,"maths_2","maths_2_author","maths_2_isbn",120.0,20);
        book3 = new Book(3L,"phy_1","phy_1_author","phy_1_isbn",130.0,30);
    }

    @Test
    void searchBooks() {

        //creating the books
        bookService.addBooks(Arrays.asList(book1,book2,book3));

        Filter filter = new Filter("maths",null,null);

        List<Book> books = searchService.searchBooks(filter);

        Assertions.assertEquals(2,books.size());

        //deleting testing data
        book1 = bookRepository.findByIsbn("maths_1_isbn");
        book2 = bookRepository.findByIsbn("maths_2_isbn");
        book3 = bookRepository.findByIsbn("phy_1_isbn");

        bookRepository.deleteById(book1.getId());
        bookRepository.deleteById(book2.getId());
        bookRepository.deleteById(book3.getId());
    }

    @Test
    void searchMediaCoverage() {

        String searchIsbn = "searchIsbn";

        book1.setIsbn(searchIsbn);
        book1.setTitle("qui");
        bookService.addBooks(Arrays.asList(book1));

        try {
            List<String> titles = searchService.searchMediaCoverage(searchIsbn);
            Assertions.assertEquals(89,titles.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //deleting testing data
        book1 = bookRepository.findByIsbn(searchIsbn);

        bookRepository.deleteById(book1.getId());
    }

    @Test
    void searchMediaCoverageWithNoPosts() {

        String searchIsbn = "searchIsbn";

        book1.setIsbn(searchIsbn);
        book1.setTitle("non-existing");
        bookService.addBooks(Arrays.asList(book1));

        try {
            List<String> titles = searchService.searchMediaCoverage(searchIsbn);
            Assertions.assertEquals(0,titles.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //deleting testing data
        book1 = bookRepository.findByIsbn(searchIsbn);

        bookRepository.deleteById(book1.getId());
    }
}