package ennum;

public enum StatusCompra {
	
//	cliente realiza Pedido
	PROCESSANDO("Pedido realizado."), 
	
//	cozinheiro prepara
	PREPARO("Pedido em faze de preparo."), 
	
//	entregador entrega
	EM_ENTREGA("Saiu para entrega."), 
	
//	entregador confirma
	CANCELADA("Cliente recusou pedido"),
	COMPLETO("Entrega Efetuada"); 
	
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
