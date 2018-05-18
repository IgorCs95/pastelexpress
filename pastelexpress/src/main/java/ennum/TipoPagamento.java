package ennum;

public enum TipoPagamento {
	
	CARTAO("Cartao"),DINHEIRO("Dinheiro");
	
	private String nome;
	
	private TipoPagamento(String nome) {
		this.nome= nome;
	}
	
	public String getNome() {
		return nome;
	}

}
