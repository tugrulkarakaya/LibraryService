package com.library.LibraryService.payload;

public interface Entity<PrimaryKey> {
	public PrimaryKey getId();
	public void setId(PrimaryKey id);
}
