# Projeto "AMOR ÀS CEGAS"

Realizado por mim e [Giovanna Oliveira](https://github.com/giovannaolvr).
).

<h2> Em geral </h2>

- O Projeto *"Amor Às Cegas"* foi um trabalho de conclusão da disciplina de Programação Orientada a Objetos, ofertada no segundo semestre do TADS, realizado em dupla.
- O projeto em si é uma aplicação de um aplicativo de relacionamentos que busca aproximar as pessoas a partir das suas preferências e hobbies.
- Esse trabalho foi realizado em conjunto com a disciplina de Banco de Dados, onde trabalhamos com o PostgreSQL.


<h2> Detalhes do Projeto </h2>

Um dos primeiros questionamentos que tivemos quando iniciamos o projeto foi: _como relacionar duas pessoas?_ Como não tínhamos experiência em processamento digital de imagens, precisávamos de um fator comum, algo que aproximasse os usuários. Logo, chegamos à ideia de utilizar os **HOBBIES** como base para as conexões, e assim nasceu o "Amor às Cegas", onde as pessoas se relacionam através de seus gostos e preferências.

Para a criação do projeto, foi necessário estabelecer quais entidades seriam necessárias para gerar o banco de dados, como cada entidade iria se relacionar e quais seriam seus atributos. Já sabíamos de duas entidades: **Usuário** e **Hobbies**. Porém, como era requisito do projeto ter quatro entidades, decidimos criar também a entidade **PagamentoVIP**, onde os usuários com pagamento VIP teriam alguns benefícios dentro da aplicação, e a entidade **Usuário_Hobbies**, que é justamente a tabela gerada a partir do relacionamento N para N entre os usuários e seus hobbies.

#### Modelagem do Banco de Dados:

- Usuario (**nickname**, _nome, login, senha, idade, genero, orientacao_sexual_)
- Hobbies (**cod_hobbie**, _descricao_)
- PagamentoVIP (**cpf**, _cod_pagamento, senha_cartao, numero_cartao, pg, **fk_usuario**_)
- Usuario_Hobbies (**id**, **_fk_usuario, fk_hobbies_**)

<sub> OBS.: O texto em **negrito** é uma chave primária e aquele em **_negrito e itálico_** é uma chave estrangeira.</sub>

A partir disso, foi possível criar o projeto e suas classes, e então detalhá-las com seus atributos e métodos. Ao examinar o arquivo _'src'_, é possível notar a presença da classe **Match** no domínio. Essa classe foi criada com o propósito de facilitar a visualização do auto-relacionamento da tabela Usuário, uma vez que um usuário se relaciona com vários outros através da quantidade de hobbies compatíveis.

#### Diagrama de Entidade Relacionamento: 

![DER 1](https://user-images.githubusercontent.com/128005290/226071582-53a93be3-7205-4dd2-9d55-e9fa18c452c8.png)

<h2>Status do projeto: Não finalizado</h2>

#### Motivos: 

- Ao realizar a operação de exclusão de um usuário, o correto seria sair do "MENU USUÁRIO". No entanto, isso não está acontecendo e, como resultado, o usuário excluído ainda tem acesso às operações, o que causa divergência entre a aplicação e o banco de dados.


