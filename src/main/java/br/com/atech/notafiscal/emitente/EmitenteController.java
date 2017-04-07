package br.com.atech.notafiscal.emitente;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.atech.notafiscal.emitente.model.EmitenteRepository;

@RestController
@Transactional
@RequestMapping("/rest/emitente")
public class EmitenteController {
	
	@Autowired
	private EmitenteRepository emitenteRepository;
	
	
	@Autowired
	private EmitenteMapper emitenteMapper;
	
	
	@GetMapping
	public List<EmitenteJson> listAll() {
		return emitenteMapper.fromEmitente(emitenteRepository.findAll());
	}

}
