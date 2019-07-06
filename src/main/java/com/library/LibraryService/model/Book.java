package com.library.LibraryService.model;

import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 75)
	private String author;
	
	@NotNull
	private Instant publishedDate;
	
	@NotNull
	private BigDecimal price;
	
	public Book() {
		
	}

	
	public Book(@NotBlank @Size(max = 75) String author, @NotNull Instant publishedDate, @NotNull BigDecimal price) {
		super();
		this.author = author;
		this.publishedDate = publishedDate;
		this.price = price;
	}


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


	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", publishedDate=" + publishedDate + ", price=" + price + "]";
	}

	
	
				
}
