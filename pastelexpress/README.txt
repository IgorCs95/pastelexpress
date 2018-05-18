* News:

- Primefaces 6.1 - https://www.primefaces.org/
- Omnifaces 2.6.5 - http://omnifaces.org/
- web.xml | https://www.primefaces.org/showcase/ui/misc/fa.xhtml | http://fontawesome.io/examples/ | https://api.jqueryui.com/theming/icons/
- locale-primefaces.js | webapp\resources\js\locale-primefaces.js | h:outputScript nas páginas onde vai ser utilizado | Fonte: https://github.com/primefaces/primefaces/wiki/Locales
- Primefaces Themes | pom.xml (all-themes, repository) | web.xml (primefaces.THEME) | https://www.primefaces.org/themes/, aba "COMMUNITY"
- Página de erro (400.xhtml) | web.xml | Exemplo de uso em: user_edit_password.xhtml quando o parâmetro na URL não é passado ou quando o identificador do usuário não é encontrado. Referências:
	* https://stackoverflow.com/questions/29838447/redirect-if-a-fviewparam-is-empty
	* http://showcase.omnifaces.org/taghandlers/viewParamValidationFailed

### Funcionalidades:
- CRUD de Usuário
- Busca por Usuário
- Geral
	* Exibição das mensagens de sucesso através do componente p:growl
	* Exibição das mensagens de erro através do componente p:messages
- Tela de criação / edição:
	* Ajax:
		- Ao escolher o grupo, os campos específicos do admin/visitante são exibidos de acordo com a escolha realizada (renderização ou não de componentes com base na escolha de alguns campos);
		- Botão do lado do login permite verificar se o login está disponível (envio parcial dos dados);
		- Nos campos específicos de visitante, atualização de selectOneMenu com base na escolha de outro selectOneMenu;
	* Outros:
		- Uso do componente p:password, incluindo confirmação da senha;
		- Uso do componente p:defaultCommand pra determinar o botão padrão ao teclar <ENTER>;
	* Regras de negócio:
		- Campos obrigatórios;
		- Armazenamento da senha em hash e não plaintext;
		- Login como campo único;

- Tela de edição da senha:
	* Campos obrigatórios;
	* Verificação se a senha atual digitada está correta;
	* Uso do componente p:password para digitação da nova senha, incluindo confirmação da senha;

- Tela principal:
	* Menu para acessar as funcionalidades:
		- Ir para a página principal;
		- Ir para a página de criação de novo usuário;
		- Abrir diálogo de criação de novo usuário;
	* Funcionalidade de filtro pelos dados do usuário;
		- Validação dos dados digitados (mensagem de erro caso algum campo não seja informado no formato esperado)
	* Listagem dos usuários com paginação;
		- Ir para a página de edição do usuário;
		- Ir para a página de edição de senha do usuário;
		- Abrir diálogo de edição de usuário;
		- Abrir diálogo de edição de senha do usuário;
		- Abrir diálogo de confirmação de remoção do usuário;
		- Tooltip nos botões (componente p:tooltip)


### Detalhes de porque fiz certas coisas / coisas interessantes que merecem destaque:
* As funcionalidades que tem página específica e que também estão sendo realizadas através de diálogos na página principal, todas elas estão reusando os respectivos Managed Beans do backend. Não é obrigado ser desta forma, mas se for possível reaproveitar, é melhor para manutenção do sistema.
* Tive que alterar o escopo do MB Index para @ViewScoped, senão o diálogo de edição exibia de forma errada quando fazíamos algum filtro que atualizava a p:dataTable e exibia valores diferentes do anterior. Era como se a dataTable não fosse atualizada e continuasse usando os valores antigos da listagem!!!
