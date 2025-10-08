package com.example.demo.dto;


import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {
    private Long newsId;
    private String title;
    private Long categoryId; // reference to Category
    private String content;
    private String author;
    private Date dateAdded;
    private Date publishDate;
    private String status;
}
