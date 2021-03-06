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
	
	/**
	 * <p>This is a description of create or edit method. This method create new entity if ID is null or 0 otherwise edits the existing record
	 * </p>
	 * @param book the book entity to be created or edited
	 * @return the Book response entity.
	 * @see 
	 * @since 1.0
	 */
	@PostMapping()	
	public BookResponse CreateOrEditBook(@RequestBody CreateOrEditBook book) {
		return bookService.createOrEditBook(book);
	}
	
	/**
	 * <p>This method finds and gets the existing Book records
	 * </p>
	 * @param base class of all entities. It contains a Long Id value. 
	 * @return the Book response entity.
	 * @see 
	 * @since 1.0
	 */
	@GetMapping()
	public BookResponse GetBook(EntityDefaultImp input) {
		return bookService.getBook(input);
	}
	
	/**
	 * <p>gets a list of books depending on proce values
	 * </p>
	 * @param lowPrice books in the list has higher price than this lower bound
	 * @param highPrice books in the list has lower price than this higher bound
	 * @param page the number of page you want to retrieve
	 * @param size the size of each page you want to retrieve
	 * @return the Book response Paged List.
	 * @see 
	 * @since 1.0
	 */
	@GetMapping("/pricerange")
	public PagedResponse<BookResponse> GetBookByPriceRange(@RequestParam String lowPrice, @RequestParam String highPrice, 
												  @RequestParam(value = "page", defaultValue = Constants.PAGE_NUMBER) int page,
												  @RequestParam(value = "size", defaultValue = Constants.PAGE_SIZE) int size){
		
		return bookService.getBooksByPriceRange(new BigDecimal(lowPrice), new BigDecimal(highPrice), page, size);
	}
	

	/**
	 * <p>Deletes book
	 * </p>
	 * @param Id the Long Id value you want to delete 
	 * @param 
	 * @return void
	 * @see 
	 * @since 1.0
	 */
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
