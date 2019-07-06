package com.library.LibraryService.payload.book;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class GetAllBooksRequest {
	
	private String filter;
	
	private String authorFilter;
	
	private String nameFilter;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate minPublishedDateFilter;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate maxPublishedDateFilter;
	
	private BigDecimal minPriceFilter;
	
	private BigDecimal maxPriceFilter;

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getAuthorFilter() {
		return authorFilter;
	}

	public void setAuthorFilter(String authorFilter) {
		this.authorFilter = authorFilter;
	}

	public LocalDate getMinPublishedDateFilter() {
		return minPublishedDateFilter;
	}

	public void setMinPublishedDateFilter(LocalDate minPublishedDateFilter) {
		this.minPublishedDateFilter = minPublishedDateFilter;
	}

	public LocalDate getMaxPublishedDateFilter() {
		return maxPublishedDateFilter;
	}

	public void setMaxPublishedDateFilter(LocalDate maxPublishedDateFilter) {
		this.maxPublishedDateFilter = maxPublishedDateFilter;
	}

	public BigDecimal getMinPriceFilter() {
		return minPriceFilter;
	}

	public void setMinPriceFilter(BigDecimal minPriceFilter) {
		this.minPriceFilter = minPriceFilter;
	}

	public BigDecimal getMaxPriceFilter() {
		return maxPriceFilter;
	}

	public void setMaxPriceFilter(BigDecimal maxPriceFilter) {
		this.maxPriceFilter = maxPriceFilter;
	}

	public String getNameFilter() {
		return nameFilter;
	}

	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}	
	
	
}
