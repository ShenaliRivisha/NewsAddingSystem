package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false) // foreign key to Category
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String author;
    private Date dateAdded;
    private Date publishDate;
    private String status; // Example: Draft, Published
}
