package com.library.LibraryService.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryService.common.Constants;
import com.library.LibraryService.payload.EntityDefaultImp;
import com.library.LibraryService.payload.PagedResponse;
import com.library.LibraryService.payload.book.BookResponse;
import com.library.LibraryService.payload.book.CreateOrEditBook;
import com.library.LibraryService.payload.book.GetAllBooksRequest;
import com.library.LibraryService.service.BookService;



@RestController
@RequestMapping("/Book")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@PostMapping()	
	public BookResponse CreateOrEditBook(@RequestBody CreateOrEditBook book) {
		return bookService.createOrEditBook(book);
	}
	
	@GetMapping()
	public BookResponse GetBook(EntityDefaultImp input) {
		return bookService.getBook(input);
	}
	
	@GetMapping("/pricerange")
	public PagedResponse<BookResponse> GetBookByPriceRange(@RequestParam String lowPrice, @RequestParam String highPrice, 
												  @RequestParam(value = "page", defaultValue = Constants.PAGE_NUMBER) int page,
												  @RequestParam(value = "size", defaultValue = Constants.PAGE_SIZE) int size){
		
		return bookService.getBooksByPriceRange(new BigDecimal(lowPrice), new BigDecimal(highPrice), page, size);
	}
	
	@DeleteMapping
	public void DeleteBook(EntityDefaultImp input) {
		bookService.deleteBook(input);
	}
	
	@GetMapping("/author")
	public PagedResponse<BookResponse> GetBookByAuthor(@RequestParam String author,
													@RequestParam(value = "page", defaultValue = Constants.PAGE_NUMBER) int page,
													@RequestParam(value = "size", defaultValue = Constants.PAGE_SIZE) int size){
		return bookService.getBooksByAuthor(author, page, size);
	}	
	
	@GetMapping("/books")
	public List<BookResponse> GetAll(GetAllBooksRequest input,
											  @RequestParam(value = "page", defaultValue = Constants.PAGE_NUMBER) int page,
											  @RequestParam(value = "size", defaultValue = Constants.PAGE_SIZE) int size){
		return bookService.getAllBooks(input, page, size);
	}
}
