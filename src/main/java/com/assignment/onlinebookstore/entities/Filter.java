package com.assignment.onlinebookstore.entities;

import lombok.*;
import org.springframework.stereotype.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Filter {
    private String ISBN;
    private String title;
    private String author;
}
