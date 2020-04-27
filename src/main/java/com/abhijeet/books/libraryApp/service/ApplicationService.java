package com.abhijeet.books.libraryApp.service;

import com.abhijeet.books.libraryApp.model.Book;
import com.abhijeet.books.libraryApp.model.Library;
import com.abhijeet.books.libraryApp.repo.BookRepositories;
import com.abhijeet.books.libraryApp.repo.LibraryRepositories;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationService {
        
        @Autowired
        private BookRepositories bookRepositories;
        
        @Autowired
        private LibraryRepositories libRepositories;
        
        /**
         * Fetch all the Book Details Available.
         * @return
         */
        public List fetchAllBookDetails() {
                List<Book> bookDetailsData = bookRepositories.findAll();
                if (CollectionUtils.isEmpty(bookDetailsData)) {
                        return new ArrayList();
                }
                return bookDetailsData;
        }
        
        /**
         * Fetch the List of Books based on a certain Parameter
         * @return
         */
        public List fetchDetailsBasedOnParam(String searchCriteria, String searchType) {
                List<Book> bookDetailsData = new ArrayList();
                if (StringUtils.equals("Book Name", searchType)) {
                        bookDetailsData.addAll(bookRepositories.findAllByBookName(searchCriteria));
                }
                if (StringUtils.equals("Author Name", searchType)) {
                        bookDetailsData.addAll(bookRepositories.findAllByAuthorName(searchCriteria));
                }
                if (StringUtils.equals("Library Name", searchType)) {
                        List<Library> libraryData = libRepositories.findAllByLibraryName(searchCriteria);
                        libraryData.stream().forEach(libraryStream ->{
                                bookDetailsData.addAll(libraryStream.getBookList());
                        });
                }
                return bookDetailsData;
        }
        
        /**
         * This method is used to save data
         *
         * @param bookDetailsParam
         */
        public void persistData(String bookDetailsParam) {
                JSONObject bookDetails = new JSONObject(bookDetailsParam);
                
                Library library = new Library();
                String libraryName = bookDetails.get("libraryName").toString();
                library.setLibraryName(libraryName);
                String location = bookDetails.get("location").toString();
                library.setLibraryLocation(location);
                String telephone = bookDetails.get("telephone").toString();
                library.setTelephone(telephone);
                Book book = populateBookDetails(bookDetails);
                List libraryDataList = new ArrayList();
                List<Library> findLibraryByName = libRepositories.findAllByLibraryName(libraryName);
                if (!CollectionUtils.isEmpty(findLibraryByName)) {
                        // Save only Book details and Update the Library Object..
                        findLibraryByName.stream().forEach(librarySavedObj -> {
                                librarySavedObj.setTelephone(telephone);
                                librarySavedObj.setLibraryLocation(location);
                                librarySavedObj.setLibraryName(libraryName);
                                librarySavedObj.addBooks(book);
                                libraryDataList.add(librarySavedObj);
                        });
                } else {
                        // Adding a new Library and Book to the DB.
                        library.addBooks(book);
                        libraryDataList.add(library);
                }
                try {
                        libRepositories.saveAll(libraryDataList);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        
        /**
         * Populate the book Object..
         * @param bookDetails
         *
         * @return
         */
        private Book populateBookDetails(JSONObject bookDetails) {
                Book book = new Book();
                book.setBookName(bookDetails.get("bookName").toString());
                book.setAuthorName(bookDetails.get("authorName").toString());
                book.setPublisherName(bookDetails.get("publisherName").toString());
                book.setAvailability(bookDetails.get("availability").toString());
                book.setBookType(bookDetails.get("bookType").toString());
                return book;
        }
}