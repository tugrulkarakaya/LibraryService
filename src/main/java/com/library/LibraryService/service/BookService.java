package com.library.LibraryService.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.library.LibraryService.model.Book;
import com.library.LibraryService.payload.Entity;
import com.library.LibraryService.payload.EntityDefaultImp;
import com.library.LibraryService.payload.EntityImpl;
import com.library.LibraryService.payload.PagedResponse;
import com.library.LibraryService.payload.book.BookResponse;
import com.library.LibraryService.payload.book.CreateOrEditBook;
import com.library.LibraryService.payload.book.GetAllBooksRequest;

public interface BookService {
	
	BookResponse createOrEditBook(CreateOrEditBook book);
	
	BookResponse getBook(EntityDefaultImp input);
	
	BookResponse deleteBook(EntityDefaultImp input);
	
	PagedResponse<BookResponse> getAllBooks(GetAllBooksRequest input, int page, int size);
	
	PagedResponse<BookResponse> getBooksByAuthor(String author, int page, int size);
	
	PagedResponse<BookResponse> getBooksByPriceRange(BigDecimal lowPrice, BigDecimal highPrice, int page, int size);	
}
