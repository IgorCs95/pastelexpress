package ennum;

public enum TipoUser {
	
	CLIENTE("Cliente"), 
	COZINHEIRO("Cozinheiro"), 
	ENTREGADOR("Entregador"),
	ADMIN("Administrador");
	
	private String nome;

	private TipoUser(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	

}
