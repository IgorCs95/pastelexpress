package ennum;

public enum StatusCompra {

	PROCESSANDO("Pedido realizado."), 
	PREPARO("Pedido em faze de preparo."), 
	EM_ENTREGA("Saiu para entrega."), 
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
