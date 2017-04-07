package br.com.atech;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Classe generica para todos os controllers do sistema.
 */
public abstract class AbstractController {

	public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";
	

	public String getBaseRequestMapping() {
		return this.getClass().getAnnotation(RequestMapping.class).value()[0];
	}

}
