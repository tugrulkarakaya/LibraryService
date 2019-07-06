package com.library.LibraryService.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.LibraryService.model.Book;
import com.library.LibraryService.repository.BookRepository;

import junit.framework.AssertionFailedError;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BookRepositorytest {

	@Autowired
    private TestEntityManager entityManager;
 
	@Autowired
	private BookRepository bookRepository; 
	
	@Test
	public void whenFindById_thenReturnBook() {
		Book book = new Book();
		book.setName("Spring Unleashed");
		book.setAuthor("Tugrul Karakaya");
		book.setPrice(new BigDecimal(100));
		book.setPublishedDate(LocalDate.of(2016, 10, 25));
		
		book = entityManager.persist(book);
		entityManager.flush();
		
		Optional<Book> found = bookRepository.findById(book.getId());
		
		assertThat(found.orElseThrow(AssertionFailedError::new).getAuthor()).isEqualTo(book.getAuthor());
	}	
	
	@Test
	public void whenFindByAuthor_thenReturnBook() {
		Book book = new Book();
		book.setName("Relativity Theory");
		book.setAuthor("Albert Einstein");
		book.setPrice(new BigDecimal(100));
		LocalDate publishDate = LocalDate.of(1935, 06, 12);
		book.setPublishedDate(publishDate);
		
		book = entityManager.persist(book);
		entityManager.flush();
		
		Pageable pageable = PageRequest.of(1,  100, Sort.Direction.DESC, "publishedDate");
		Page<Book> found = bookRepository.findByAuthor(book.getAuthor(), pageable); 
		Page<Book> found2 = bookRepository.findByAuthor("Unknown Author", pageable);
		
		assertThat(found.getTotalElements()).isGreaterThanOrEqualTo(1);
	}	
	
	@Test
	public void whenFindByName_thenReturnBook() {
		Book book = new Book();
		book.setName("Snow");
		book.setAuthor("Orhan Pamuk");
		book.setPrice(new BigDecimal(100));
		LocalDate publishDate = LocalDate.of(2002, 05, 11);
		book.setPublishedDate(publishDate);
		
		book = entityManager.persist(book);
		entityManager.flush();
		
		Pageable pageable = PageRequest.of(1,  100, Sort.Direction.DESC, "publishedDate");
		
		Page<Book> found = bookRepository.findByName(book.getName(), pageable); 						
		Page<Book> found2 = bookRepository.findByName("Non-Exists Book", pageable);		
		
		assertThat(found.getTotalElements()).isGreaterThanOrEqualTo(1);
		assertThat(found2.getTotalElements()).isEqualTo(0);
		
	}	
}
