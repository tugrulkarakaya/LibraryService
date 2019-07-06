package com.library.LibraryService.exception;

public class LibraryAppException extends RuntimeException {

	 public LibraryAppException(String message) {
	        super(message);
	    }

	    public LibraryAppException(String message, Throwable cause) {
	        super(message, cause);
	    }
}
