package com.assignment.onlinebookstore.services;

import com.assignment.onlinebookstore.Utils.*;
import com.assignment.onlinebookstore.entities.*;
import com.assignment.onlinebookstore.entities.Responses.*;
import com.assignment.onlinebookstore.entities.requests.*;
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
class OrderServiceTest {

    Book book1;
    Book book2;
    Book book3;

    @Autowired
    BookService bookService;

    @Autowired
    OrderService orderService;

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    public void init() {
        book1 = new Book(1L,"maths","maths_author","maths_isbn",110.0,10);
        book2 = new Book(2L,"phy","phy_author","phy_isbn",120.0,20);
        book3 = new Book(3L,"chem","chem_author","chem_isbn",130.0,30);
    }

    @Test
    void placeOrder() {

        //creating the book
        bookService.addBooks(Arrays.asList(book1));
        Book book = bookRepository.findByIsbn(book1.getIsbn());

        //placing order
        OrderRequest orderRequest = new OrderRequest(book.getId(),3);
        OrderResponse orderResponse = orderService.placeOrder(Arrays.asList(orderRequest)).get(0);

        //checking if order placed
        Assertions.assertEquals(ORDER_STATUS.PLACED,orderResponse.getStatus());

        //checking if qty reduced after placing order
        book = bookRepository.findByIsbn(book1.getIsbn());
        Assertions.assertEquals(7,book.getQuantity());

        //deleting testing data
        bookRepository.deleteById(book.getId());
    }

    @Test
    void placeOrderWithInsufficientQty() {

        //creating the book
        bookService.addBooks(Arrays.asList(book1));
        Book book = bookRepository.findByIsbn(book1.getIsbn());

        //placing order
        OrderRequest orderRequest = new OrderRequest(book.getId(),15);
        OrderResponse orderResponse = orderService.placeOrder(Arrays.asList(orderRequest)).get(0);

        //checking if order placed
        Assertions.assertEquals(ORDER_STATUS.PLACED,orderResponse.getStatus());
        Assertions.assertTrue(orderResponse.getError().contains(Constants.INSUFFICIENT_QTY));
        
        book = bookRepository.findByIsbn(book1.getIsbn());

        //deleting testing data
        bookRepository.deleteById(book.getId());
    }

    @Test
    void placeInvalidOrder() {

        //placing order
        OrderRequest orderRequest = new OrderRequest(12345L,3); //12345 is invalid book id
        OrderResponse orderResponse = orderService.placeOrder(Arrays.asList(orderRequest)).get(0);

        //checking if order placed
        Assertions.assertEquals(ORDER_STATUS.FAILED,orderResponse.getStatus());
    }
}