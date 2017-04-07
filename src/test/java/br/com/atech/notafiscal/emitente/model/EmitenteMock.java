package br.com.atech.notafiscal.emitente.model;

public class EmitenteMock {

	public static Emitente any() {
		Emitente emitente = new Emitente();
		emitente.setId(1L);
		return emitente;
	}
	
}