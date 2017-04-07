package br.com.atech.mercadoria.model;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.data.domain.PageImpl;

import br.com.atech.mercadoria.MercadoriaJson;

public class MercadoriaMock {
	
	public static PageImpl<Mercadoria> anyPage()
	{
		return new PageImpl<Mercadoria>(Arrays.asList(any()), null, 1);
	}
	
	public static Mercadoria any()
	{
		Mercadoria mercadoria = new Mercadoria();
		mercadoria.setId(1L);
		return mercadoria;
	}
	
	public static Mercadoria mercadoriaComplete()
	{
		Mercadoria mercadoria = new Mercadoria();
		mercadoria.setId(1L);
		mercadoria.setCodigo("1234");
		mercadoria.setNome("nome");
		mercadoria.setPreco(BigDecimal.ONE);
		return mercadoria;
	}
	
	public static MercadoriaJson anyJson()
	{
		MercadoriaJson mercadoria = new MercadoriaJson();
		mercadoria.setId(1L);
		return mercadoria;
	}
	
	public static PageImpl<MercadoriaJson> anyPageJson()
	{
		return new PageImpl<MercadoriaJson>(Arrays.asList(anyJson()), null, 1);
	}
	

}
