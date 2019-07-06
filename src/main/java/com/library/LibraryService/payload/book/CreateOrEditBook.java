package com.library.LibraryService.payload.book;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateOrEditBook {
	
	private Long id;
	
	@NotBlank
	@Size(max = 75)
	private String author;
	
	@NotNull
	private Instant publishedDate;
	
	@NotNull
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

	@Override
	public String toString() {
		return "CreateOrEditBook [id=" + id + ", author=" + author + ", publishedDate=" + publishedDate + ", price="
				+ price + "]";
	}
	
	
}
