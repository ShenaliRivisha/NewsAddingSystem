package com.example.demo.service;

import com.example.demo.dto.NewsDTO;
import java.util.List;

public interface NewsService {
    NewsDTO createNews(NewsDTO newsDTO);
    NewsDTO getNewsById(Long id);
    List<NewsDTO> getAllNews();
    NewsDTO updateNews(Long id, NewsDTO newsDTO);
    void deleteNews(Long id);
}

