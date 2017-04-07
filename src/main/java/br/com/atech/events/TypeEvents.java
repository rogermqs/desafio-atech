package br.com.atech.events;

public enum TypeEvents {
	
	
	ERROR(" error registered "),
	IN_PROCESSING (" in processing "),
	SUCCESS(" successfully registered ");
	
	public String message;
	
	private TypeEvents(String message)
	{
		this.message = message;
	}

}
