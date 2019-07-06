package com.library.LibraryService.repository;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import com.library.LibraryService.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
	Optional<Book> findById(Long bookId);	
	
	Page<Book> findByAuthor(String author, Pageable pageable);
    
    Page<Book> findByPriceBetween(BigDecimal lowPrice, BigDecimal highPrice, Pageable pageable);

    List<Book> findByIdIn(List<Long> bookIds);

    List<Book> findByIdIn(List<Long> bookIds, Sort sort);
}
