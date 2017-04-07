package br.com.atech.notafiscal;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.atech.mercadoria.MercadoriaMapper;
import br.com.atech.notafiscal.emitente.EmitenteMapper;
import br.com.atech.notafiscal.model.NotaFiscal;

@Mapper(uses = { MercadoriaMapper.class, EmitenteMapper.class }, componentModel = "spring")
public interface NotaFiscalMapper {
	
	
	NotaFiscalMapper MAPPER = Mappers.getMapper(NotaFiscalMapper.class);
	
	NotaFiscal toNotaFiscal(NotaFiscalJson notaFiscalJson);

    @InheritInverseConfiguration
    NotaFiscalJson fromNotaFiscal(NotaFiscal notaFiscal);
    
    
    @InheritInverseConfiguration
    List<NotaFiscalJson> fromNotaFiscal(List<NotaFiscal> notaFiscal);
    

}
