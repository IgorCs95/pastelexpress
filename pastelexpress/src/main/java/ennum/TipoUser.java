package ennum;

public enum TipoUser {
	
	CLIENTE("Cliente"), FUNCIONARIO("Funcionario");
	
	private String nome;

	private TipoUser(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	

}
