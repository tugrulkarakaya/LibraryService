package com.library.LibraryService.repository;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Sort;
import com.library.LibraryService.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
	Optional<Book> findById(Long bookId);	
	
	@Query("SELECT b FROM Book b WHERE b.author LIKE CONCAT('%',:author,'%')")
	Page<Book> findByAuthor(@Param("author")  String author, Pageable pageable);
	
	@Query("SELECT b FROM Book b WHERE b.name LIKE CONCAT('%',:name,'%')")
	Page<Book> findByName(@Param("name")String name, Pageable pageable);
    
    Page<Book> findByPriceBetween(BigDecimal lowPrice, BigDecimal highPrice, Pageable pageable);

    List<Book> findByIdIn(List<Long> bookIds);
}
