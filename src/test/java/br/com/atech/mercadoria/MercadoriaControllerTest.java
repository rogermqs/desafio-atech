package br.com.atech.mercadoria;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import br.com.atech.GenericControllerTest;
import br.com.atech.mercadoria.model.Mercadoria;
import br.com.atech.mercadoria.model.MercadoriaMock;
import br.com.atech.mercadoria.model.MercadoriaRepository;

@RunWith( MockitoJUnitRunner.class )
public class MercadoriaControllerTest extends GenericControllerTest<Mercadoria>{
	
	
	@InjectMocks
	private MercadoriaController controller;

	@Mock
	private MercadoriaRepository mercadoriaRepository;
	
	
	
	@Before
	public void setup() {
		super.setup(this.controller);
	}
	
	@Test
	public void testIfFindOneOk() {
		Page<Mercadoria> anyPage = MercadoriaMock.anyPage();
		Pageable page = Mockito.any(Pageable.class);
		Specification<Mercadoria> specification = Mockito.any(Specification.class);
		when(mercadoriaRepository.findall;
		findAllOk(Arrays.asList(MercadoriaMock.any()));
	}
}
