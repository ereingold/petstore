package com.ndgits.petstore.service.model;

public class PetNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8134994161746257692L;

	public PetNotFoundException(){
		
	}
	public PetNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
