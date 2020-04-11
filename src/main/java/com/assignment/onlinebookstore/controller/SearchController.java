package com.assignment.onlinebookstore.controller;

import com.assignment.onlinebookstore.Utils.*;
import com.assignment.onlinebookstore.entities.*;
import com.assignment.onlinebookstore.services.*;
import io.swagger.annotations.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(value="Search Controller", description="Search books")
public class SearchController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SearchService searchService;

    @ApiOperation(value="Search books by author or title or isbn",response = ArrayList.class)
    @PostMapping("books/search")
    public ResponseEntity<List<Book>> searchBooks(@ApiParam(value = Constants.SEARCH_BOOK_API_VALUE) @RequestBody Filter filter){
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

    @ApiOperation(value="Search posts by title of book's isbn",response = ArrayList.class)
    @GetMapping("books/search/media")
    public ResponseEntity<List<String>> searchMediaCoverage(@ApiParam(value = Constants.SEARCH_MEDIA_COVERAGE_API_VALUE)
                                                                @RequestParam String isbn){
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
