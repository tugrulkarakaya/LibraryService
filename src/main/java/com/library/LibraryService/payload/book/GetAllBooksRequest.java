package com.library.LibraryService.payload.book;

import java.math.BigDecimal;
import java.time.Instant;

public class GetAllBooksRequest {
	
	private String filter;
	
	private String authorFilter;
	
	private String nameFilter;
	
	private Instant minPublishedDateFilter;
	
	private Instant maxPublishedDateFilter;
	
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

	public Instant getMinPublishedDateFilter() {
		return minPublishedDateFilter;
	}

	public void setMinPublishedDateFilter(Instant minPublishedDateFilter) {
		this.minPublishedDateFilter = minPublishedDateFilter;
	}

	public Instant getMaxPublishedDateFilter() {
		return maxPublishedDateFilter;
	}

	public void setMaxPublishedDateFilter(Instant maxPublishedDateFilter) {
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
