package com.library.LibraryService.service;

import java.math.BigDecimal;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.library.LibraryService.exception.NotFoundResourceException;
import com.library.LibraryService.model.Book;
import com.library.LibraryService.payload.EntityDefaultImp;
import com.library.LibraryService.payload.PagedResponse;
import com.library.LibraryService.payload.book.BookResponse;
import com.library.LibraryService.payload.book.CreateOrEditBook;
import com.library.LibraryService.payload.book.GetAllBooksRequest;
import com.library.LibraryService.repository.BookRepository;

public class BookServiceImp implements BookService {
	
	@Autowired
	private BookRepository bookRepository; 
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public BookResponse createOrEditBook(CreateOrEditBook book) {
		if(book.getId() == null) {
			//Create new Book
			return create(book);
		}
		else {
			//Update book
			return edit(book);
		}
	}

	private BookResponse create(CreateOrEditBook input) {
		Book book = mapper.map(input, Book.class);		
		book = bookRepository.save(book);
		return mapper.map(book,BookResponse.class);		
	}
	
	private BookResponse edit(CreateOrEditBook input) {
		Book book = bookRepository.findById(input.getId()).orElseThrow(() -> new NotFoundResourceException("Book", "id", input.getId()));
		mapper.map(input, book);
		book = bookRepository.save(book);
		return mapper.map(book, BookResponse.class);		
	}
	
	@Override
	public BookResponse getBook(EntityDefaultImp input) {
		Book book = bookRepository.findById(input.getId()).orElseThrow(() -> new NotFoundResourceException("Book", "id", input.getId()));		
		return mapper.map(book, BookResponse.class);
	}

	@Override
	public BookResponse deleteBook(EntityDefaultImp input) {
		bookRepository.deleteById(input.getId());
		return null;
	}

	@Override
	public PagedResponse<BookResponse> getAllBooks(GetAllBooksRequest input, int page, int size) {
		
		return null;
	}

	@Override
	public PagedResponse<BookResponse> getBooksByAuthor(String author, int page, int size) {
		
		return null;
	}

	@Override
	public PagedResponse<BookResponse> getBooksByPriceRange(BigDecimal lowPrice, BigDecimal highPrice, int page,
			int size) {
		
		return null;
	}

}
