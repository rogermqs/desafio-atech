package br.com.atech.notafiscal.emitente;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.atech.notafiscal.emitente.model.Emitente;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmitenteMapper {
	
	
	EmitenteMapper MAPPER = Mappers.getMapper(EmitenteMapper.class);
	
	Emitente toEmitente(EmitenteJson emitenteJson);
	
	List<Emitente> toEmitente(List<EmitenteJson> emitenteJson);
	
	@InheritInverseConfiguration
	EmitenteJson fromEmitente(Emitente emitente);
	
	@InheritInverseConfiguration
	List<EmitenteJson> fromEmitente(List<Emitente> emitente);
	
	
	
}
