package com.assignment.onlinebookstore.services;

import com.assignment.onlinebookstore.Utils.*;
import com.assignment.onlinebookstore.entities.*;
import com.assignment.onlinebookstore.repositories.*;
import com.assignment.onlinebookstore.services.interfaces.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;

@Service
public class SearchService implements SearchIface {

    @Autowired
    BookRepository bookRepository;

    @Value("${media.url}")
    String mediaUrl;


    @Override
    public List<Book> searchBooks(Filter filter) {
        List<Book> books = new ArrayList<>();

        if(filter.getAuthor()==null)
            filter.setAuthor("NO_VALUE");
        if(filter.getISBN()==null)
            filter.setISBN("NO_VALUE");
        if(filter.getTitle()==null)
            filter.setTitle("NO_VALUE");

        books = bookRepository.findByFilter(filter.getAuthor(),filter.getTitle(),filter.getISBN());

        return books;
    }

    @Override
    public List<String> searchMediaCoverage(String isbn)throws Exception {
        List<String> postTitles = new ArrayList<>();

        List<Post> posts = fetchPosts();
        Book book = bookRepository.findByIsbn(isbn);

        if(book==null)
            return postTitles;

        List<Post> filteredPosts = filterPosts(posts,book.getTitle());

        return getTitles(filteredPosts);
    }

    private List<String> getTitles(List<Post> posts){
        List<String> titles = new ArrayList<>();

        for (Post post:
             posts) {
            titles.add(post.getTitle());
        }

        return titles;
    }

    private List<Post> filterPosts(List<Post> posts,String title){
        List<Post> filteredPosts = new ArrayList<>();

        for (Post post:
             posts) {
            if(post.getTitle().contains(title) || post.getBody().contains(title))
                filteredPosts.add(post);
        }

        return filteredPosts;
    }

    private List<Post> fetchPosts()throws Exception{
        StringBuffer stringBufferPosts = new StringBuffer();
        try {
            stringBufferPosts = RestUtils.makeGetCall(mediaUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();

        List<Post> posts = null;
        try {
            posts = objectMapper.readValue(stringBufferPosts.toString(),new TypeReference<ArrayList<Post>>() {});

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception();
        }

        return posts;
    }
}
