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

import br.com.atech.GenericControllerTest;
import br.com.atech.mercadoria.model.Mercadoria;
import br.com.atech.mercadoria.model.MercadoriaMock;
import br.com.atech.mercadoria.model.MercadoriaRepository;
import br.com.atech.mercadoria.model.MercadoriaService;

@RunWith( MockitoJUnitRunner.class )
public class MercadoriaControllerTest extends GenericControllerTest<Mercadoria>{
	
	
	@InjectMocks
	private MercadoriaController controller;

	@Mock
	private MercadoriaRepository mercadoriaRepository;
	
	@Mock
	private MercadoriaService mercadoriaService;
	
	@Mock
	private MercadoriaMapper mercadoriaMapper;
	
	
	@Before
	public void setup() {
		super.setup(this.controller);
	}
	
	@Test
	public void testFindById() {
		Mercadoria mercadoria = MercadoriaMock.any();
		when(mercadoriaRepository.findOne(Mockito.anyLong())).thenReturn(mercadoria);
		when(mercadoriaMapper.fromMercadoria(Mockito.any(Mercadoria.class))).thenReturn(MercadoriaMock.anyJson());
		findById(1L);
	}
	
	@Test
	public void testFindByNomeOrCode() {
		endPoint+="/search";
		Mercadoria mercadoria = MercadoriaMock.any();
		when(mercadoriaRepository.findByCodigoSistemaOrDescricaoIgnoreCaseContaining(Mockito.anyString())).thenReturn(Arrays.asList(mercadoria));
		when(mercadoriaMapper.fromMercadoria(Mockito.anyListOf(Mercadoria.class))).thenReturn(Arrays.asList(MercadoriaMock.anyJson()));
		findByData("anyString");
	}
	
	
	@Test
	public void testSaveMercadoriaAttributoObrigatorioNaoPreenchido() {
		Mercadoria any = MercadoriaMock.any();
		when(mercadoriaMapper.toMercadoria(Mockito.any(MercadoriaJson.class))).thenReturn(MercadoriaMock.any());
		createReturnCreateBadRequest(any);
	}
	
	@Test
	public void testSaveSuccess() {
		when(mercadoriaMapper.toMercadoria(Mockito.any(MercadoriaJson.class))).thenReturn(MercadoriaMock.any());
		createReturnCreate(MercadoriaMock.mercadoriaComplete());
	}
	
	@Test
	public void testUpdateSuccess() {
		when(mercadoriaMapper.toMercadoria(Mockito.any(MercadoriaJson.class))).thenReturn(MercadoriaMock.any());
		updateReturn(MercadoriaMock.mercadoriaComplete());
	}
	
	
	@Test
	public void testDeleteMercadoriaSuccess() {
		deleteOk(1L);
	}
}
