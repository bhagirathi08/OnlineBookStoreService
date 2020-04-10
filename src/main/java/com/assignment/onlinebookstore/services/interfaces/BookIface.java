package com.assignment.onlinebookstore.services.interfaces;

import com.assignment.onlinebookstore.entities.*;
import java.util.*;

public interface BookIface {
    public Map<String,String> addBooks(List<Book> books);
}
