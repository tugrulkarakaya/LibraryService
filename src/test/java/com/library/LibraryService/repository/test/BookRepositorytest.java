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
}
