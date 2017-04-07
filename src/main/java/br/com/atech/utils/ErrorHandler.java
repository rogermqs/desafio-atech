package br.com.atech;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.atech.utils.AtechException;


/**
 * Spring controller advice
 */
@ControllerAdvice
public class ErrorHandler {
	/**
     * Handling from all exception
     * @param error Exception
     * @return Custom JSON Envelope Error
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handle(final Exception error) {
    	error.printStackTrace();
        final ErrorDTO errorDTO = new ErrorDTO(error.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * Handling from all exception
     * @param error EcptException
     * @return Custom JSON Envelope Error
     */
    @ResponseBody
    @ExceptionHandler(AtechException.class)
    public ResponseEntity<ErrorDTO> handle(final AtechException error) {
    	error.printStackTrace();
        final ErrorDTO errorDTO = new ErrorDTO(error.getMessage());
        return new ResponseEntity<>(errorDTO, error.getHttpStatus());
    }
    
    /**
     * Handling from all exception
     * @param error ServletRequestBindingException
     * @return Custom JSON Envelope Error
     */
    @ResponseBody
    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<ErrorDTO> handle(final ServletRequestBindingException error) {
    	error.printStackTrace();
        final ErrorDTO errorDTO = new ErrorDTO(error.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.UNAUTHORIZED);
    }
}