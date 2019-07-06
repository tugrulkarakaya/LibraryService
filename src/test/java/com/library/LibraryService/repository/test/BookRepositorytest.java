package com.library.LibraryService.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
	
	@Test
	public void whenFindByPriceBetween() {
		Book book1 = new Book("Book1", "Author1", LocalDate.now(), new BigDecimal(400));
		Book book2 = new Book("Book1", "Author1", LocalDate.now(), new BigDecimal(500));
		Book book3 = new Book("Book1", "Author1", LocalDate.now(), new BigDecimal(600));
		Book book4 = new Book("Book1", "Author1", LocalDate.now(), new BigDecimal(900));
		
		book1 = entityManager.persist(book1);
		book2 = entityManager.persist(book2);
		book3 = entityManager.persist(book3);
		book4 = entityManager.persist(book4);
		
		entityManager.flush();
		
		Pageable pageable = PageRequest.of(1,  100, Sort.Direction.DESC, "publishedDate");
		
		Page<Book> founds1 = bookRepository.findByPriceBetween(new BigDecimal(450), new BigDecimal(650), pageable);
		Page<Book> founds2 = bookRepository.findByPriceBetween(new BigDecimal(350), new BigDecimal(950), pageable);
		Page<Book> founds3 = bookRepository.findByPriceBetween(new BigDecimal(150), new BigDecimal(350), pageable);
						
		assertThat(founds1.getTotalElements()).isEqualTo(2);
		assertThat(founds2.getTotalElements()).isEqualTo(4);
		assertThat(founds3.getTotalElements()).isEqualTo(0);		
	}
	
	
	@Test
	public void whenFindByIdIn() {
		Book book1 = new Book("Book1", "Author1", LocalDate.now(), new BigDecimal(15));
		Book book2 = new Book("Book1", "Author1", LocalDate.now(), new BigDecimal(22));
		Book book3 = new Book("Book1", "Author1", LocalDate.now(), new BigDecimal(33));
		Book book4 = new Book("Book1", "Author1", LocalDate.now(), new BigDecimal(45));
		
		book1 = entityManager.persist(book1);
		book2 = entityManager.persist(book2);
		book3 = entityManager.persist(book3);
		book4 = entityManager.persist(book4);
		
		entityManager.flush();
		
		Pageable pageable = PageRequest.of(1,  100, Sort.Direction.DESC, "publishedDate");
		
		Optional<Book> found1 = bookRepository.findById(book1.getId());
		
		ArrayList<Long> Ids = new ArrayList<Long>();
		Ids.add(book2.getId());
		Ids.add(book3.getId());
		
		List<Book> founds2 = bookRepository.findByIdIn(Ids);
						
		assertThat(found1.orElseThrow(AssertionFailedError::new).getAuthor()).isEqualTo(book1.getAuthor());
		assertThat(founds2.size()).isEqualTo(2);
		
	}
}
