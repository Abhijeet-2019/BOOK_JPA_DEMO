package com.abhijeet.books.libraryApp.controllers;

import com.abhijeet.books.libraryApp.model.Book;
import com.abhijeet.books.libraryApp.model.Library;

import com.abhijeet.books.libraryApp.repo.LibraryRepositories;
import com.abhijeet.books.libraryApp.service.ApplicationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/librariesDetails")
@CrossOrigin
@Scope("prototype")
public class LibrariesDetailsController {

    @Autowired
    ApplicationService applicationService;



    private ResponseObject responseObject;

    LibrariesDetailsController() {
        responseObject = new ResponseObject();
    }

    @CrossOrigin
    @RequestMapping(headers = {"Accept=application/json"},
            value = "/fetchAllBookDetails", method = RequestMethod.GET)
    public ResponseObject fetchAllBookDetails() {
        List<Book> bookData = applicationService.fetchAllBookDetails();
        if (CollectionUtils.isEmpty(bookData)) {
            responseObject.setResponseStatus("Unable to Fetch Data from DB");
        } else {
            responseObject.setResponseStatus("SUCCESS");
            List responseData = new ArrayList();
            bookData.forEach(book -> {
                book.getLibrary().setBookList(new HashSet<>());
                responseData.add(book);
            });
            responseObject.setResponseData(responseData);
        }
        return responseObject;
    }

    @CrossOrigin
    @RequestMapping(headers = {"Accept=application/json"},
            value = "/fetchBookByName", method = RequestMethod.GET)
    public ResponseObject fetchBookByName(
            @RequestParam(value = "searchCriteria") String searchCriteria,
            @RequestParam(value = "searchType") String searchType) {
        List<Book> bookList = applicationService.fetchDetailsBasedOnParam(searchCriteria, searchType);
        if (CollectionUtils.isEmpty(bookList)) {
            responseObject.setResponseStatus("Unable to Fetch Data from DB");
        } else {
            List responseData = new ArrayList();
            bookList.forEach(book -> {
                book.getLibrary().setBookList(new HashSet<>());
                responseData.add(book);
            });
            responseObject.setResponseData(responseData);
        }
        return responseObject;
    }

    @CrossOrigin
    @RequestMapping(value = "/savebookDetails" , method = RequestMethod.POST)
    public ResponseObject submitData(@RequestBody String bookDetailsParam){
        try {
            applicationService.persistData(bookDetailsParam);
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseObject;
    }
}
