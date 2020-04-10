package com.assignment.onlinebookstore.controller;

import com.assignment.onlinebookstore.entities.*;
import com.assignment.onlinebookstore.services.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BookController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping(){
        return "PONG PONG 2";
    }

    @PostMapping("books")
    public ResponseEntity<Map<String,String>> addBooks(@RequestBody List<Book> books){
        logger.info("Adding books "+books.toString());

        Map<String,String> body = null;
        try {
            body = bookService.addBooks(books);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
