package br.com.atech;

import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;


@Component
public class AtechErrorHandler implements ErrorHandler{

	@Override
	public void handleError(Throwable t) {
		System.out.println(t.getMessage());
	}

}
