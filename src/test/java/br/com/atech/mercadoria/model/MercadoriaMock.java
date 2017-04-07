package br.com.atech.mercadoria.model;

import java.util.Arrays;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class MercadoriaMock {
	
	
	
	public static Page<Mercadoria> anyPage()
	{
		return new PageImpl<Mercadoria>(Arrays.asList(any()), null, 1);
	}
	
	public static Mercadoria any()
	{
		Mercadoria mercadoria = new Mercadoria();
		mercadoria.setId(1L);
		return mercadoria;
	}
	

}
