package ennum;

public enum StatusCompra {

	PENDENTE("Pagamento pendente no aguardo da liberação do pagamento."), 
	POCESSANDO("Pagamento concluído enviando o produto ao usuário."), 
	EM_ESPERA("Problema no recebimento ou na entrega da compra produto (on hold)."), 
	COMPLETO("Compra concluída faturamento efetuado"), 
	FECHADO("Compra cancelada por falta de pagamento ou envio."), 
	CANCELADA("Compra cancelada pelo usuário.");
	
	private String nome;

	private StatusCompra(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
