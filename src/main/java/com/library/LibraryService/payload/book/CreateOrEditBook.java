package com.library.LibraryService.payload.book;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class CreateOrEditBook {
	
	private Long id;
	
	@NotBlank
	@Size(max = 75)
	private String author;
	
	@NotBlank
	@Size(max = 500)
	private String name;
	
	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate publishedDate;
	
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

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CreateOrEditBook [id=" + id + ", author=" + author + ", name=" + name + ", publishedDate="
				+ publishedDate + ", price=" + price + "]";
	}

	
	
	
}
