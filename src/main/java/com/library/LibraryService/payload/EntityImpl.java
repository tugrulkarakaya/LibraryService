package com.library.LibraryService.payload;

public class EntityImpl<PrimaryKey> implements Entity<PrimaryKey>{
	
	PrimaryKey Id;
	
	public EntityImpl() {

	}
	
	public EntityImpl(PrimaryKey id) {
		super();
		Id = id;
	}
	
	public PrimaryKey getId() {
		return Id;
	}
	public void setId(PrimaryKey id) {
		Id = id;
	}
	
	
}
