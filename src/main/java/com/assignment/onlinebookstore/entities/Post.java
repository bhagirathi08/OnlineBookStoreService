package com.assignment.onlinebookstore.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.io.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post implements Serializable {
    private String userId;
    private String id;
    private String title;
    private String body;
}
