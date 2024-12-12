Sistema de Mensagens - Gerenciamento de Usuários e Mensagens Enviadas
Este projeto tem como objetivo implementar um sistema para o gerenciamento de usuários e mensagens enviadas. O sistema foi desenvolvido utilizando Java com a API JDBC para interação com o banco de dados MySQL e inclui uma interface de linha de comando para interação com o usuário.

Funcionalidades
Cadastro de Usuários: Permite o registro, visualização, edição e exclusão de usuários no sistema.
Envio de Mensagens: Gerencia o envio de mensagens entre usuários, com registro do conteúdo e da data do envio.
Listagem de Mensagens: Exibe as mensagens enviadas por cada usuário.
Autenticação de Usuários: Realiza a autenticação de usuários com base em credenciais como CPF e senha.
Tecnologias Utilizadas
Linguagem: Java
Banco de Dados: MySQL
Bibliotecas e Ferramentas:
API JDBC
IntelliJ IDEA
Maven para gerenciamento de dependências
JUnit para testes unitários
Estrutura de Classes
Main
Classe principal que gerencia a interação com o usuário. Permite a manutenção de usuários, envio de mensagens e autenticação.

ConexaoBD
Classe responsável por gerenciar a conexão com o banco de dados MySQL.

Métodos:
conectar(): Estabelece e retorna a conexão com o banco de dados.
fecharConexao(): Fecha a conexão com o banco de dados.
GerenciadorTabelas
Classe que verifica e cria as tabelas necessárias no banco de dados.

Métodos:
verificarOuCriarTabelas(): Garante a existência das tabelas necessárias para o sistema.
Usuario
Representa um usuário no sistema.

Atributos:
cpf, senha, email, nome
Métodos:
Getters e Setters
UsuarioRepository
Realiza operações CRUD para a tabela de usuários e a autenticação.

Métodos:
adicionarUsuario()
listarUsuarios()
alterarUsuario()
excluirUsuario()
autenticarUsuario()
Mensagem
Representa uma mensagem enviada no sistema.

Atributos:
idMensagem, conteudo, dataEnvio, idUsuarioRemetente, idUsuarioDestinatario
Métodos:
Getters e Setters
MensagemRepository
Realiza operações CRUD para as mensagens enviadas.

Métodos:
enviarMensagem()
listarMensagensEnviadas()
listarMensagensRecebidas()
excluirMensagem()
Configuração de Strings de Conexão
A configuração correta das strings de conexão é essencial para que o sistema funcione corretamente. O sistema utiliza a classe ConexaoBD para gerenciar a conexão com o banco de dados. Certifique-se de configurar as seguintes informações:

URL de Conexão: A URL de conexão para o banco de dados MySQL. Exemplo:

String url = "jdbc:mysql://localhost:3306/sistema_mensagens";
Usuário: O nome de usuário para acessar o banco de dados. Exemplo:

String usuario = "root";
Senha: A senha para o usuário do banco de dados. Exemplo:

String senha = "sua_senha";
O método conectar() na classe ConexaoBD deve ser configurado da seguinte forma:



public Connection conectar() throws SQLException {
    String url = "jdbc:mysql://localhost:3306/sistema_mensagens";
    String usuario = "root";
    String senha = "sua_senha";
    return DriverManager.getConnection(url, usuario, senha);
}
Certifique-se de que o servidor MySQL esteja em execução e que o banco de dados sistema_mensagens tenha sido criado antes de executar o programa.

Estrutura do Banco de Dados
O sistema utiliza as seguintes tabelas:

usuarios: Contém informações dos usuários (CPF, senha, email, nome).
mensagens: Armazena as mensagens enviadas (ID da mensagem, conteúdo, data de envio, remetente, destinatário).
Como Executar o Projeto
Configure as strings de conexão na classe ConexaoBD com os parâmetros corretos para o seu ambiente.
Certifique-se de que o banco de dados esteja configurado e rodando no MySQL.
Compile o projeto utilizando Maven:


mvn clean install
Execute o programa principal:


java -jar target/sistema-mensagens.jar
