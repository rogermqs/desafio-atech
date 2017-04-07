package br.com.atech.notafiscal;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.atech.AbstractController;
import br.com.atech.notafiscal.model.NotaFiscal;
import br.com.atech.notafiscal.model.NotaFiscalRepository;
import br.com.atech.notafiscal.model.NotaFiscalService;
import br.com.atech.notafiscal.model.NotaFiscalSpecification;

@RestController
@Transactional
@RequestMapping("/rest/nota-fiscal")
public class NotaFiscalController extends AbstractController{
	
	
	@Autowired
	private NotaFiscalRepository notaFiscalRepository;
	
	@Autowired
	private NotaFiscalService notaFiscalService;
	
	@Autowired
	private NotaFiscalMapper notaFiscalMapper;
	
	
	@RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Page<NotaFiscalJson> filter( final FiltroNotaFiscal filtro, final Pageable pageable ) {
		Page<NotaFiscal> pageNota = notaFiscalRepository.findAll(NotaFiscalSpecification.searchNotaFiscal(filtro), pageable);
		return new PageImpl<NotaFiscalJson>(notaFiscalMapper.fromNotaFiscal(pageNota.getContent()), pageable, pageNota.getTotalElements());
	}

	@ResponseStatus( value = HttpStatus.CREATED )
	@PostMapping
	public void create( @RequestBody @Valid final NotaFiscalJson notaFiscalJson ) {
		notaFiscalService.saveNota(notaFiscalMapper.toNotaFiscal(notaFiscalJson));
	}

	@PutMapping
	public void update( @RequestBody @Valid final NotaFiscalJson notaFiscalJson ) {
		notaFiscalService.saveNota(notaFiscalMapper.toNotaFiscal(notaFiscalJson));
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	public void removeList( @PathVariable final Long id ) {
		notaFiscalRepository.delete(id);
	}

	@GetMapping( value = "/{id}")
	public NotaFiscalJson findOne( @PathVariable final Long id ) {
		return notaFiscalMapper.fromNotaFiscal(notaFiscalRepository.findOne(id));

	}

}
