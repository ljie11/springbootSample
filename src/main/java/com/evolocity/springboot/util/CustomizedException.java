package com.evolocity.springboot.util;


public class CustomizedException extends RuntimeException {

    /**
	 *  A customized Exception for Product action errors
	 */
	private static final long serialVersionUID = 3438404171152243317L;
	private String errorMessage;

    public CustomizedException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
