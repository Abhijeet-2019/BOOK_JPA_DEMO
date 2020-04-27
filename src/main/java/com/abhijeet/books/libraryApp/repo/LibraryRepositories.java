package com.abhijeet.books.libraryApp.repo;


import com.abhijeet.books.libraryApp.model.Book;
import com.abhijeet.books.libraryApp.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface LibraryRepositories extends JpaRepository<Library,Integer> {

        public List<Library> findAllByLibraryName(String libraryName);
        
}
