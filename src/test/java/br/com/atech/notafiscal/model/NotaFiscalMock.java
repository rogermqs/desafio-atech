package br.com.atech.notafiscal.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;

import org.springframework.data.domain.PageImpl;

import br.com.atech.mercadoria.model.MercadoriaMock;
import br.com.atech.notafiscal.NotaFiscalJson;
import br.com.atech.notafiscal.emitente.model.EmitenteMock;

public class NotaFiscalMock{
	
	
	
	public static PageImpl<NotaFiscal> anyPage()
	{
		return new PageImpl<NotaFiscal>(Arrays.asList(any()), null, 1);
	}
	
	public static NotaFiscal any()
	{
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setId(1L);
		return notaFiscal;
	}
	
	public static NotaFiscal notaFiscalComplete()
	{
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setId(1L);
		notaFiscal.setEmitente(EmitenteMock.any());
		notaFiscal.setDataEmissao(Calendar.getInstance());
		notaFiscal.setNroNota(1);
		notaFiscal.setMercadorias(new HashSet<>());
		notaFiscal.getMercadorias().add(MercadoriaMock.mercadoriaComplete());
		notaFiscal.setValorTotal(BigDecimal.ONE);
		return notaFiscal;
	}
	
	public static NotaFiscalJson anyJson()
	{
		NotaFiscalJson notaFiscal = new NotaFiscalJson();
		notaFiscal.setId(1L);
		return notaFiscal;
	}
	
	public static PageImpl<NotaFiscalJson> anyPageJson()
	{
		return new PageImpl<NotaFiscalJson>(Arrays.asList(anyJson()), null, 1);
	}
	
}
