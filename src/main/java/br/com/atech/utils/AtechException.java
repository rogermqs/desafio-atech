package br.com.atech.utils;

import org.springframework.http.HttpStatus;


/**
 * AtechException
 */
public class AtechException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    private final HttpStatus httpStatus;
    
    /**
     * Exception
     * @param message message error
     * @param httpStatus status http
     */
    public AtechException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
    
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}