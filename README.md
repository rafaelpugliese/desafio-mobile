# Desafio de programação mobile (IOS/Android)

## Informações do autor

Nome: Rafael Fernandes Pugliese de Morais

Email: rafaelfpm@gmail.com

Linkedin: www.linkedin.com/in/rafael-pugliese

## Requisitos cumpridos

- Utilização de um arquivo .gitignore;
- Implementação de testes unitários;
- Implementação de testes de usuarios;
- Utilização de padrões de projetos;
- Realização de persistência de dados locais;
- Utilização de Cache para imagens;

## Configuração e execução do projeto

Projeto foi desenvolvido na plataforma Android e foi configurado a partir da pasta raiz do repositório. Para execução do projeto é utilizada a ferramenta Android Studio e não é necessário nenhum procedimento a mais do que o padrão para execução de aplicações android na IDE.

## Observações de funcionalidades dos projetos:

- Aos exibir os últimos itens de cada lista (Repositórios e Pulls), a aplicação carrega mais itens (caso exista) e acrescenta os itens ao final de cada respectiva lista;
- A medida que um dado (dados dos repositórios, dos pulls requests e usuários) é carregado online, eles são armazenados (em thread separada) e utilizados quando o dispositivo estiver sem internet. Os dados quando listados offline, são listados com a mesma ordem de quando listados online;
- Além dos dados dos protótipos foi acrescentado o status de cada pull request (open, closed e merged);
- Suportar mudanças de orientação das telas sem perder estado;


## Observações técnicas do projeto:

- É utilizada uma biblioteca (room) para criação do banco de dados (SQlite). A biblioteca room também é utilizada para realizar as operações com o banco de dados e mapeamento necessário.
- Para exibição dos itens das listas, foi utilizada a biblioteca recyclerview, esta biblioteca da suporte para exibir varios layouts de itens em uma mesma lista, porém esse recurso não foi necessário por conta do contexto da aplicação. 
- Para visualizar informações do banco de dados, é utilizada a biblioteca amitshekhar. Quando a aplicação está sendo executada, basta acessar pelo browser de um computador, que esteja na mesma rede, o endereço http://ip_dispositivo:8080 para verificar as informações.
- São utilizadas as bibliotecas mockito e junit para implementação dos testes (testes de usuário e teste unitário) da aplicação.
- Para a conversão de json/objetos foi utilizada a biblioteva GSON;
- Aplicação possuie sistema de build Gradle;
- Foram feitas pequenas customizações no estilo (Theme.Material) da aplicação. Foram feitos ajustes de cores de alguns componentes e modo de transições (utilização de slides) entre telas;
- Através da utilização de interfaces, as operações solicitadas são direcionadas (nas classes de *AsyncTask) para métodos específicos (nas camadas abaixos) de acordo com o status da conectividade (online/offline). Quando online, são feitas requisições na API do GitHub, quando offline, são realizadas consultas no banco de dados local do dispositivo;
- No projeto foi utilizada uma arquitetura em camadas;