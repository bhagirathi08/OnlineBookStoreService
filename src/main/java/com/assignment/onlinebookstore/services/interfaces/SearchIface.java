package com.assignment.onlinebookstore.services.interfaces;

import com.assignment.onlinebookstore.entities.*;

import java.util.*;

public interface SearchIface {
    public List<Book> searchBooks(Filter filter);
    public List<String> searchMediaCoverage(String isbn)throws Exception;
}
