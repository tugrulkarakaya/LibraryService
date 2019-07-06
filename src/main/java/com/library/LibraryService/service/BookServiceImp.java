package com.library.LibraryService.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.library.LibraryService.common.Constants;
import com.library.LibraryService.exception.LibraryAppException;
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
		checkPagination(page, size);
		
		Pageable pageable = PageRequest.of(page,  size, Sort.Direction.DESC, "author");
		Page<Book> books = bookRepository.findByAuthor(author, pageable);
		
		if(books.getNumberOfElements() == 0) {
			return new PagedResponse(Collections.<BookResponse>emptyList() ,books.getNumber(), books.getSize(), books.getTotalElements(), books.getTotalPages(),books.isLast());			
		}
		
		List<BookResponse> bookResponse = books.map(b -> mapper.map(b, BookResponse.class)).getContent();		
		return new PagedResponse<BookResponse>(bookResponse, books.getNumber(), books.getSize(), books.getTotalElements(), books.getTotalPages(), books.isLast());
	}

	@Override
	public PagedResponse<BookResponse> getBooksByPriceRange(BigDecimal lowPrice, BigDecimal highPrice, int page,
			int size) {
		if(lowPrice.compareTo(highPrice)>0) {
			throw new LibraryAppException("Price range parameters is not correct. lowPrice must be equal or less than highPrice");
		}
		
		checkPagination(page, size);
		
		Pageable pageable = PageRequest.of(page,  size, Sort.Direction.ASC, "price");
		Page<Book> books = bookRepository.findByPriceBetween(lowPrice, highPrice, pageable);
		
		if(books.getNumberOfElements() == 0) {
			return new PagedResponse(Collections.<BookResponse>emptyList() ,books.getNumber(), books.getSize(), books.getTotalElements(), books.getTotalPages(),books.isLast());			
		}
		
		List<BookResponse> bookResponse = books.map(b -> mapper.map(b, BookResponse.class)).getContent();		
		return new PagedResponse<BookResponse>(bookResponse, books.getNumber(), books.getSize(), books.getTotalElements(), books.getTotalPages(), books.isLast());
		
	}

	private void checkPagination(int page, int size) {
        if(page < 0) {
            throw new LibraryAppException("Page number must be greater than zero.");
        }

        if(size > Constants.MAX_PAGE_SIZE) {
            throw new LibraryAppException("Page size must be less than " + Constants.MAX_PAGE_SIZE);
        }
    }
	
}
