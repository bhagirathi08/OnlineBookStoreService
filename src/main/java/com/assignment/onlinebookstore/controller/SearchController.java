package com.assignment.onlinebookstore.controller;

import com.assignment.onlinebookstore.entities.*;
import com.assignment.onlinebookstore.services.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class SearchController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SearchService searchService;

    @PostMapping("books/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestBody Filter filter){
        logger.info("Searching for books  by Filter: "+filter.toString());

        List<Book> body = null;
        try {
            body = searchService.searchBooks(filter);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("books/search/media")
    public ResponseEntity<List<String>> searchMediaCoverage(@RequestParam String isbn){
        logger.info("Searching for media coverage  by ISBN: "+isbn);

        List<String> body = null;
        try {
            body = searchService.searchMediaCoverage(isbn);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
