package ennum;

public enum OrdenarItem {
	
//	cliente realiza Pedido
	MAIOR("Decrescente"), 
	
//	cozinheiro prepara
	MENOR("Crescente"); 
	
	
	private String nome;

	private OrdenarItem(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
