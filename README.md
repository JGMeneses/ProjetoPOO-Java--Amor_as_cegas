# Projeto "AMOR ÀS CEGAS"

Realizado por mim e [João Meneses](https://github.com/JGMeneses).

<h2> Em geral </h2>

- O Projeto *"Amor Às Cegas"* foi um trabalho de conclusão da disciplina de Programação Orientada a Objetos, ofertada no segundo semestre do TADS, realizado em dupla.
- O projeto em si é uma aplicação de um aplicativo de relacionamentos que busca aproximar as pessoas a partir das suas preferências e hobbies.
- Esse trabalho foi realizado em conjunto com a disciplina de Banco de Dados, onde trabalhamos com o PostgreSQL.


<h2> Detalhes do Projeto </h2>

Um dos primeiros questionamentos que tivemos quando iniciamos o projeto foi: _como relacionar duas pessoas?_ Como não tínhamos experiência em processamento digital de imagens, precisávamos de um fator comum, algo que aproximasse os usuários. Logo, chegamos à ideia de utilizar os **HOBBIES** como base para as conexões, e assim nasceu o "Amor às Cegas", onde as pessoas se relacionam através de seus gostos e preferências.

Para a criação do projeto, foi necessário estabelecer quais entidades seriam necessárias para gerar o banco de dados, como cada entidade iria se relacionar e quais seriam seus atributos. Já sabíamos de duas entidades: **Usuário** e **Hobbies**. Porém, como era requisito do projeto ter quatro entidades, decidimos criar também a entidade **PagamentoVIP**, onde os usuários com pagamento VIP teriam alguns benefícios dentro da aplicação, e a entidade **Usuário_Hobbies**, que é justamente a tabela gerada a partir do relacionamento N para N entre os usuários e seus hobbies.

