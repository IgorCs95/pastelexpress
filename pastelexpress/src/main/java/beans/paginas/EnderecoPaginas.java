package beans.paginas;

public final class EnderecoPaginas {

	public EnderecoPaginas() {
		throw new UnsupportedOperationException("Esta classe não deve ser instanciada!");
	}

	private static final String REDIRECT_TRUE = "?faces-redirect=true";

	public static final String PAGINA_PRINCIPAL = "/paginas/protegido/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_SESSION_TIMEOUT = "/paginas/publico/sessionTimeout.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_ERROR_CODE_500 = "/paginas/publico/500.xhtml" + REDIRECT_TRUE;
	
	
	public static final String PAGINA_G_EDIT_USER = "/paginas/protegido/gerencia/gerencia_user.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_G_EDIT_ITEM = "/paginas/protegido/gerencia/gerencia_item.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_F_LISTA_PEDIDOS = "/paginas/protegido/funcionarios/lista_pedidos.xhtml" + REDIRECT_TRUE;
	
	
	
}
