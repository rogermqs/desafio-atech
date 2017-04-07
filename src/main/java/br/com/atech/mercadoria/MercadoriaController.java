package br.com.atech.mercadoria;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.atech.AbstractController;
import br.com.atech.mercadoria.model.Mercadoria;
import br.com.atech.mercadoria.model.MercadoriaRepository;
import br.com.atech.mercadoria.model.MercadoriaService;
import br.com.atech.mercadoria.model.MercadoriaSpecification;

@RestController
@Transactional
@RequestMapping("/rest/mercadoria")
public class MercadoriaController extends AbstractController{

	
	
	@Autowired
	private MercadoriaRepository mercadoriaRepository;
	
	@Autowired
	private MercadoriaService mercadoriaService;
	
	@Autowired
	private MercadoriaMapper mercadoriaMapper;
	
	
	@GetMapping(value = "/filter")
	public Page<MercadoriaJson> filter( final FiltroMercadoria filtro, final Pageable pageable ) {
		Page<Mercadoria> pageNota = mercadoriaRepository.findAll(MercadoriaSpecification.searchMercadoria(filtro), pageable);
		return new PageImpl<MercadoriaJson>(mercadoriaMapper.fromMercadoria(pageNota.getContent()), pageable, pageNota.getTotalElements());
	}
	
	
	@GetMapping(value = "/search/{name}")
	public List<MercadoriaJson> searchNomeOrCodigo( @PathVariable final String name ) {
		return mercadoriaMapper.fromMercadoria(mercadoriaRepository.findByCodigoSistemaOrDescricaoIgnoreCaseContaining(name));
	}

	@ResponseStatus( value = HttpStatus.CREATED )
	@PostMapping
	public void create( @RequestBody @Valid final MercadoriaJson mercadoriaJson ) {
		mercadoriaService.save(mercadoriaMapper.toMercadoria(mercadoriaJson));
	}

	@PutMapping
	public void update( @RequestBody @Valid final MercadoriaJson mercadoriaJson ) {
		mercadoriaService.save(mercadoriaMapper.toMercadoria(mercadoriaJson));
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	public void removeList( @PathVariable final Long id ) {
		mercadoriaRepository.delete(id);
	}

	@GetMapping( value = "/{id}")
	public MercadoriaJson findOne( @PathVariable final Long id ) {
		return mercadoriaMapper.fromMercadoria(mercadoriaRepository.findOne(id));

	}
}
