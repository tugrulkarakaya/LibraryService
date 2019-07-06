package com.library.LibraryService.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.library.LibraryService.model.Book;
import com.library.LibraryService.payload.book.GetAllBooksRequest;

public interface BookRepositoryCustom {
	
	List<Book> findAllBooksWithPagination(GetAllBooksRequest bookRequest, Pageable pageable);
	
}
