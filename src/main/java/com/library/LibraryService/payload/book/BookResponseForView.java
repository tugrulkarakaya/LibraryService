package com.library.LibraryService.payload.book;

public class BookResponseForView {
	
	BookResponse bookResponse;
	/*
		//if there is another related data that wanted to be added to the response entity (like AuthorDetail info or, Publisher Info etc can be added here)
		//Given sampel does not contain any related entity that data can be flatten
		Author author;
		Publisher publisher	
	*/
	
	public BookResponse getBookResponse() {
		return bookResponse;
	}

	public void setBookResponse(BookResponse bookResponse) {
		this.bookResponse = bookResponse;
	}	
}
