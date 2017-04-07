package br.com.atech.notafiscal;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.atech.GenericControllerTest;
import br.com.atech.notafiscal.model.NotaFiscal;
import br.com.atech.notafiscal.model.NotaFiscalMock;
import br.com.atech.notafiscal.model.NotaFiscalRepository;
import br.com.atech.notafiscal.model.NotaFiscalService;

@RunWith( MockitoJUnitRunner.class )
public class NotaFiscalControllerTest extends GenericControllerTest<NotaFiscal>{
	
	
	@InjectMocks
	private NotaFiscalController controller;

	@Mock
	private NotaFiscalRepository notaFiscalRepository;
	
	@Mock
	private NotaFiscalService notaFiscalService;
	
	@Mock
	private NotaFiscalMapper notaFiscalMapper;
	
	
	@Before
	public void setup() {
		super.setup(this.controller);
	}
	
	@Test
	public void testFindById() {
		NotaFiscal notaFiscal = NotaFiscalMock.any();
		when(notaFiscalRepository.findOne(Mockito.anyLong())).thenReturn(notaFiscal);
		when(notaFiscalMapper.fromNotaFiscal(Mockito.any(NotaFiscal.class))).thenReturn(NotaFiscalMock.anyJson());
		findById(1L);
	}
	
	
	@Test
	public void testSaveMercadoriaAttributoObrigatorioNaoPreenchido() {
		NotaFiscal any = NotaFiscalMock.any();
		when(notaFiscalMapper.toNotaFiscal(Mockito.any(NotaFiscalJson.class))).thenReturn(NotaFiscalMock.any());
		createReturnCreateBadRequest(any);
	}
	
	@Test
	public void testSaveSuccess() {
		when(notaFiscalMapper.toNotaFiscal(Mockito.any(NotaFiscalJson.class))).thenReturn(NotaFiscalMock.any());
		createReturnCreate(NotaFiscalMock.notaFiscalComplete());
	}
	
	@Test
	public void testUpdateSuccess() {
		when(notaFiscalMapper.toNotaFiscal(Mockito.any(NotaFiscalJson.class))).thenReturn(NotaFiscalMock.any());
		updateReturn(NotaFiscalMock.notaFiscalComplete());
	}
	
	
	@Test
	public void testDeleteMercadoriaSuccess() {
		deleteOk(1L);
	}
}
