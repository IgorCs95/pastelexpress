package ennum;

public enum TipoUser {
	
	CLIENTE("Clente"), FUNCIONARIO("Funcionario"),  ENTREGADOR("Entregador");
	
	private String nome;

	private TipoUser(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	

}
