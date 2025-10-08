package com.example.demo.service;

import com.example.demo.dto.NewsDTO;
import com.example.demo.model.Category;
import com.example.demo.model.News;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.NewsRepository;
//import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public NewsDTO createNews(NewsDTO newsDTO) {
        Category category = categoryRepository.findById(newsDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        News news = new News();
        news.setTitle(newsDTO.getTitle());
        news.setCategory(category);
        news.setContent(newsDTO.getContent());
        news.setAuthor(newsDTO.getAuthor());
        news.setDateAdded(newsDTO.getDateAdded());
        news.setPublishDate(newsDTO.getPublishDate());
        news.setStatus(newsDTO.getStatus());

        News saved = newsRepository.save(news);
        newsDTO.setNewsId(saved.getNewsId());
        return newsDTO;
    }

    @Override
    public NewsDTO getNewsById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));

        return new NewsDTO(
                news.getNewsId(),
                news.getTitle(),
                news.getCategory().getCategoryId(),
                news.getContent(),
                news.getAuthor(),
                news.getDateAdded(),
                news.getPublishDate(),
                news.getStatus()
        );
    }

    @Override
    public List<NewsDTO> getAllNews() {
        return newsRepository.findAll().stream()
                .map(n -> new NewsDTO(
                        n.getNewsId(),
                        n.getTitle(),
                        n.getCategory().getCategoryId(),
                        n.getContent(),
                        n.getAuthor(),
                        n.getDateAdded(),
                        n.getPublishDate(),
                        n.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public NewsDTO updateNews(Long id, NewsDTO newsDTO) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));

        Category category = categoryRepository.findById(newsDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        news.setTitle(newsDTO.getTitle());
        news.setCategory(category);
        news.setContent(newsDTO.getContent());
        news.setAuthor(newsDTO.getAuthor());
        news.setDateAdded(newsDTO.getDateAdded());
        news.setPublishDate(newsDTO.getPublishDate());
        news.setStatus(newsDTO.getStatus());

        News updated = newsRepository.save(news);
        newsDTO.setNewsId(updated.getNewsId());
        return newsDTO;
    }

    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
