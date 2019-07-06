package com.library.LibraryService.payload.book;

import java.math.BigDecimal;
import java.time.Instant;

import javax.validation.constraints.Size;

import com.library.LibraryService.payload.EntityImpl;

public class BookResponse extends EntityImpl<Long> {
	
	private Long id;
	
	@Size(max = 75)
	private String author;
	
	private Instant publishedDate;
	
	private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Instant getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Instant publishedDate) {
		this.publishedDate = publishedDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
