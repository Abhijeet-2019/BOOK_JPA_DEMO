package com.abhijeet.books.libraryApp.model;


import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Entity
@Table(name = "LIBRARY_TABLE")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIBRARY_ID")
    private int libraryId;

    @Column(name = "LIBRARY_NAME")
    private String libraryName;

    @Column(name = "LIBRARY_LOCATION")
    private String libraryLocation;

    @Column(name = "TELEPHONE")
    private String telephone;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "library")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Book> bookList;


    public Set<Book> getBookList() {
        return bookList;
    }

    public void setBookList(Set<Book> bookList) {
        this.bookList = bookList;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryLocation() {
        return libraryLocation;
    }

    public void setLibraryLocation(String libraryLocation) {
        this.libraryLocation = libraryLocation;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void addBooks(Book book) {
        if(bookList == null ){
            bookList = new HashSet<>();
        }
        bookList.add(book);
        book.setLibrary(this);
    }
}
