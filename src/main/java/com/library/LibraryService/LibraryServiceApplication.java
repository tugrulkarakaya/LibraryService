package com.library.LibraryService;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.library.LibraryService.service.BookService;
import com.library.LibraryService.service.BookServiceImp;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		LibraryServiceApplication.class,
		DozerBeanMapper.class
})
public class LibraryServiceApplication {

	@Bean
	public DozerBeanMapper dozerBeanMapper() {
		//Dozer have some problems with JDK 8. It took me 45 min to solve this poblem. 
		List<String> mappingFiles = new ArrayList<String>();
		mappingFiles.add("dozerJdk8Converters.xml");

		DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
		dozerBeanMapper.setMappingFiles(mappingFiles);
		return dozerBeanMapper;
	}
	
	@Bean
	public BookService bookService() {
		return new BookServiceImp();
	}
	
	@PostConstruct
	void init() {
		//To Eliminate any app server's time zone effect
		//TimeZone.setDefault(TimeZone.getTimeZone("UTC"));			
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryServiceApplication.class, args);
	}

}
