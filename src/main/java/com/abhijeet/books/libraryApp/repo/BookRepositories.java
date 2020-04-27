package com.abhijeet.books.libraryApp.repo;

import com.abhijeet.books.libraryApp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface  BookRepositories extends JpaRepository<Book,Integer> {
 
         List<Book> findAllByBookName(String bookName);
        public List<Book> findAllByAuthorName(String authorName);
}
