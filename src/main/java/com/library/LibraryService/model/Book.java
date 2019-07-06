package com.library.LibraryService.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

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
	private String Author;
	
	@NotNull
	private Instant PublishedDate;
	
	@NotNull
	private BigDecimal price;
	
	public Book() {
		
	}

	
	public Book(@NotBlank @Size(max = 75) String author, @NotNull Instant publishedDate, @NotNull BigDecimal price) {
		super();
		Author = author;
		PublishedDate = publishedDate;
		this.price = price;
	}


	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public Instant getPublishedDate() {
		return PublishedDate;
	}

	public void setPublishedDate(Instant publishedDate) {
		PublishedDate = publishedDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", Author=" + Author + ", PublishedDate=" + PublishedDate + ", price=" + price + "]";
	}			
}
