package com.abhijeet.books.libraryApp.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@Entity
@Table(name = "BOOKS_TABLE")
public class Book implements  Serializable{
    
    private static final long serialVersionUID = 1L;
    public Book(){
    
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    public int bookId;
    @Column(name = "BOOK_NAME")
    private String bookName;
    @Column(name = "AUTHOR_NAME")
    private String authorName;
    @Column(name = "BOOK_TYPE")
    private String bookType;
    @Column(name = "PUBLISHER_NAME")
    private String publisherName;
    @Column(name = "AVAILABILITY")
    private  String availability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="LIBRARY_ID")
    @Fetch(FetchMode.JOIN)
    private  Library library;
    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
            this.availability = availability;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
