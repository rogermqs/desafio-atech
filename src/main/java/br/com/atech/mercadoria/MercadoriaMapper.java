package br.com.atech.mercadoria;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.atech.mercadoria.model.Mercadoria;

@Mapper(componentModel = "spring")
public interface MercadoriaMapper {
	
	
	MercadoriaMapper MAPPER = Mappers.getMapper(MercadoriaMapper.class);
	
	Mercadoria toMercadoria(MercadoriaJson mercadoriaJson);

    @InheritInverseConfiguration
    MercadoriaJson fromMercadoria(Mercadoria mercadoria);
    
    @InheritInverseConfiguration
    List<MercadoriaJson> fromMercadoria(List<Mercadoria> mercadoria);
    

}
