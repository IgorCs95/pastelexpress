### Como configurar o JDBC Realm no Wildfly

1. NÃO implante a aplicação ainda antes de realizar todas essas configurações;
2. Certifique-se que o Wildfly está executando;
3. As configurações serão feitas via linha de comando. Para isso, acessem a pasta "<WILDFLY_HOME>\bin" e executem o seguinte comando:

* No windows:
jboss-cli.bat

* No Linux/Mac:
./jboss-cli.sh

4. Dentro da interface via linha de comando, se conecte no Widfly através do comando "connect":

[disconnected /] connect

5. Conectado, você deve agora estar visualizando a linha de comando da seguinte forma:

[standalone@localhost:9990 /]

6. Agora, execute a seguinte linha de comando:

./subsystem=security/security-domain=pastelexpressJdbcRealm/:add(cache-type=default)

7. O resultado deve ser algo do tipo: ""outcome" => "success"". Este comando acima irá criar o nosso JDBC Realm chamado "dacaJdbcRealm". Caso queira criar algum com outro nome, basta alterar esta informação no comando acima. Execute a seguinte linha de comando para configurá-lo (note que nele referencia o "JNDI name" da conexão com o banco de dados que já foi criado anteriormente no campo "dsJndiName", faça a devida adequação para seu cenário):

./subsystem=security/security-domain=pastelexpressJdbcRealm/authentication=classic:add(login-modules=[{code=Database, flag=Required, module-options={ \
    dsJndiName="java:/pastelexpress_PostgreSQL", \
    principalsQuery="select senha from public.tb_user where login = ?", \
    rolesQuery="select tipo, 'Roles' from public.tb_user where login = ?", \
    hashAlgorithm="SHA-256", \
    hashEncoding="base64" \
}}])

8. Feito isso, após você deve visualizar na saída algo do tipo: ""outcome" => "success"".

9. Para completar a configuração, é necessário fazer um reload no wildfly. Para isso, execute a seguinte linha de comando:

reload

10. Este comando acima não deve apresentar nenhuma saída.

11. Pronto, você acaba de configurar o JDBC Realm no Wildfly. Tente agora implantar a aplicação.


################################# F.A.Q. #################################

1) Como fazer reload do Wildfly via CLI?

Execute a seguinte linha de comando:

reload

2) Como remover um Realm configurado?

./subsystem=security/security-domain=<nome do realm>/:remove()

Ex: ./subsystem=security/security-domain=pastelexpressJdbcRealm/:remove()
