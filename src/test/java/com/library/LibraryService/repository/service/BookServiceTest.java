package com.library.LibraryService.repository.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.internal.util.MockUtil.isMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.LibraryService.common.Constants;
import com.library.LibraryService.controller.BookController;
import com.library.LibraryService.exception.LibraryAppException;
import com.library.LibraryService.model.Book;
import com.library.LibraryService.payload.EntityDefaultImp;
import com.library.LibraryService.payload.PagedResponse;
import com.library.LibraryService.payload.book.BookResponse;
import com.library.LibraryService.payload.book.CreateOrEditBook;
import com.library.LibraryService.payload.book.GetAllBooksRequest;
import com.library.LibraryService.repository.BookRepository;
import com.library.LibraryService.service.BookService;
import com.library.LibraryService.service.BookServiceImp;
import static org.mockito.Mockito.*;
import junit.framework.AssertionFailedError;


@RunWith(SpringRunner.class)
public class BookServiceTest {

	@TestConfiguration
    static class BookServiceImplTestContextConfiguration { 
        @Bean
        public BookService bookService() {
            return new BookServiceImp();
        }
        
        @Bean
        public DozerBeanMapper dozerBeanMapper() {
        	List<String> mappingFiles = new ArrayList();
        	mappingFiles.add("dozerJdk8Converters.xml");

        	DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        	dozerBeanMapper.setMappingFiles(mappingFiles);
        	return dozerBeanMapper;
        }
    }
	
	@Autowired
	private BookService bookService;
	
	@MockBean
	private BookRepository bookRepository;
	
	Book bookCreate = null;
	Book bookCreate2 = null;
	
	@Before
	public void setUp() {
		Book book1 = new Book("Book Name1", "Book Author1", LocalDate.of(2011, 12, 15), new BigDecimal(40));
		book1.setId(1L);
		Book book2 = new Book("Book Name2", "Book Author1", LocalDate.of(2011, 12, 16), new BigDecimal(50));
		book2.setId(2L);
		Book book3 = new Book("Book Name3", "Book Author1", LocalDate.of(2011, 12, 17), new BigDecimal(60));
		book3.setId(3L);
		Book book4 = new Book("Book Name4", "Book Author2", LocalDate.of(2011, 12, 18), new BigDecimal(70));
		book4.setId(4L);
		Book book5 = new Book("Book Name5", "Book Author2", LocalDate.of(2011, 12, 19), new BigDecimal(80));
		book5.setId(5L);
				
		bookCreate = new Book("New Book", "New Author",LocalDate.of(2000, 11, 28), new BigDecimal(9));
		bookCreate2 = new Book("New Book", "New Author",LocalDate.of(2000, 11, 28), new BigDecimal(9));
		bookCreate2.setId(9999L);

	    Mockito.when(bookRepository.findById(book1.getId())).thenReturn(Optional.of(book1));
	    Mockito.when(bookRepository.findById(book2.getId())).thenReturn(Optional.of(book2));
	    Mockito.when(bookRepository.findById(book3.getId())).thenReturn(Optional.of(book3));
	    Mockito.when(bookRepository.findById(book4.getId())).thenReturn(Optional.of(book4));
	    Mockito.when(bookRepository.findById(book5.getId())).thenReturn(Optional.of(book5));
	    
	    List<Book> books1 =  new ArrayList<Book>();
	    books1.add(book1);books1.add(book2); books1.add(book3);	  
	    List<Book> books2 =  new ArrayList<Book>();
	    books2.add(book4);books2.add(book5);	  
	    Page<Book> pageBook = new PageImpl<Book>(books1);	    
	    Mockito.when(bookRepository.findByAuthor(eq("Book Author1"), any())).thenReturn(pageBook);
	    
	    
	    //GetAllBooksRequest bookRequest = new GetAllBooksRequest();
	    //bookRequest.setFilter("Book");
	    List<Book> books3 = new ArrayList<>();
	    books3.addAll(books1); books3.addAll(books2);	    
	    Mockito.when(bookRepository.findAllBooksWithPagination(any(), any())).thenReturn(books3);
	    
	    
	    Mockito.when(bookRepository.save(any())).thenReturn(bookCreate2);
	    Mockito.when(bookRepository.findByPriceBetween(any(), any(), any())).thenReturn(pageBook);
	}	
	
	@Test
	public void testGetBook() {
		EntityDefaultImp entity = new EntityDefaultImp();
		entity.setId(1L);
		
		BookResponse found = bookService.getBook(entity);
		
		assertThat(found.getName()).isEqualTo("Book Name1");
	}
	
	@Test
	public void testCreateUpdateBook() {
		
		BookResponse found = bookService.createOrEditBook(new CreateOrEditBook());		
		assertThat(found.getName()).isEqualTo(bookCreate.getName());
	}
	
	@Test
	public void testGetAllBooks() {
		GetAllBooksRequest input = new GetAllBooksRequest();
		
		List<BookResponse> founds = bookService.getAllBooks(input, 1, 100);
		
		assertThat(founds.size()).isEqualTo(5);
	}
	
	@Test
	public void testGetbookByPriceRange() {
		PagedResponse<BookResponse> pagedBooks = bookService.getBooksByPriceRange(new BigDecimal(0), new BigDecimal(1000), 1, 30);
		assertThat(pagedBooks.getContent().size()).isEqualTo(3);	}
	
	@Test
	public void testGetBooksByAuthor() {
		
		
		PagedResponse<BookResponse> foundByAuthor =  bookService.getBooksByAuthor("Book Author1", 1, 30);		
		assertThat(foundByAuthor.getContent().size()).isEqualTo(3);			
	}
	
	@Test
	public void testPaginationException() {
		try{
			//make page size 1 more than maxpagesize which exceeds page size 50 and validate exception type
			PagedResponse<BookResponse> foundByAuthor =  bookService.getBooksByAuthor("Book Author1", 1, Constants.MAX_PAGE_SIZE+1);
		} catch(Exception ex)
		{
			assertTrue(ex instanceof LibraryAppException);
		}	    
	}
}
