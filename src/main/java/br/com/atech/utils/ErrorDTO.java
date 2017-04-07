package br.com.atech;

import java.io.Serializable;


/**
 * ErrorDTO
 */
public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor
	 * @param message message error
	 */
	public ErrorDTO(String message)
	{
		this.message = message;
	}
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage( String message ) {
		this.message = message;
	}
}