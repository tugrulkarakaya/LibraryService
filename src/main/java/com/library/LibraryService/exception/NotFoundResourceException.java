package com.library.LibraryService.exception;

public class NotFoundResourceException extends LibraryAppException {				
	private String resource;
	private String field;
    private Object value;

    public NotFoundResourceException( String resource, String field, Object value) {
        super(String.format("%s not found with %s : '%s'", resource, field, value));
        this.resource= resource;
        this.field= field;
        this.value = value;
    }

	public String getResource() {
		return resource;
	}

	public String getField() {
		return field;
	}

	public Object getValue() {
		return value;
	}
}
