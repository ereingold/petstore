package com.ndgits.petstore.service.model;

public class PetIdMismatchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8134994161746257692L;

	public PetIdMismatchException(){
		
	}
	public PetIdMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
