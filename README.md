# Dezconto

# Sumário

### 1.COMPONENTES<br>
Ezequiel Mapel(ezequiel.f.mapel@gmail.com)<br> 
Pedro Henrique(pedroso.hts@gmail.com)<br>
Ramom Matieli(ramommatieli@hotmail.com)<br>

### 2.INTRODUÇÃO E MOTIVAÇAO<br>
O projeto Dezconto consiste em uma aplicação similar a um clube de vantagens, na qual o usuário poderá fazer buscas por descontos, afim de conseguir economizar na hora de ir as compras. Devido a atual situação econômica que enfrentamos em nosso país, muitos buscam economizar o máximo possível. Com isso em mente, veio a ideia de criar uma aplicação que fornece descontos nas compras em lojas diversas. <br>

### 3.MINI-MUNDO<br>

O Site terá em sua página inicial a opção de **LOJISTA, LOGIN e INFORMAÇÕES DO APP** Para o Lojista se cadastrar, deverá fazê-lo pelo site, informando **RAZÃO SOCIAL, NOME FANTASIA, CNPJ, SENHA, CONFIRMAÇÃO DA SENHA e INCLUSÃO DE FOTO(opcional).** Agora, se o lojista estiver cadastrado e for realizar o **LOGIN**, necessariamente, terá que informar o cnpj e a senha, inseridos no ato de **CADASTRO DE LOJISTA**. Para lojistas que esqueceram a senha há a possibilidade de clicar em **ESQUECI MINHA SENHA**, esta opção pedirá o Email do Lojista, após isso, o mesmo será direciona a uma página em que requererá a senha enviada automaticamente pelo sistema ao email informado, juntamente com a nova senha e a confirmação de senha. Já para o Usuário, este deverá se cadastrar pela aplicação mobile.

O app mobile terá em sua página inicial a opção de **LOGIN COM FACEBOOK, GMAIL OU EMAIL, CADASTRAR, E TERMOS DE USO**. Caso o Usuário opte por se cadastrar com email, deverá informar **NOME COMPLETO, EMAIL, SENHA, CONFIRMAÇÃO DA SENHA.**

Ao entrar, o Lojista encontrará a opção de **CADASTRAR DESCONTO**, informando o desconto oferecido, se é em toda loja, ou em algum produto específico, ou categoria. O Usuário, por sua vez, terá acesso então a sessão dos **CUPONS DISPONÍVEIS**. Os cupons possuirão a foto da loja (se houver), nome da loja e detalhes do cupom (quantidade de desconto, quantidade de cupom daquele mesmo tipo, etc). Ao escolher algum cupom, será gerado um código de validação.

### 4.RASCUNHOS BÁSICOS DA INTERFACE (MOCKUPS)<br>

**SITE**

![Alt text](https://github.com/Ramom-Matieli17/DezcontoApp/blob/master/prints/Desconto-site.png)
<hr>

**APP**

![Alt text](https://github.com/Ramom-Matieli17/DezcontoApp/blob/master/prints/Dezconto-app.PNG)
<br><br>
[Código do projeto mobile](https://github.com/ezequielmapel/Dezconto/tree/dev)
<hr><br>

### 5.COMO RODAR A APLICAÇÃO WEB <br>
Escolhemos 'dockerizar' nossa aplicação para aumentar a sua portabilidade, logo abaixo estará as etapas a serem seguidas para rodar o container da aplicação.
<br><br><br>
**1- Certifique-se**<br>
  Para começar, certifique-se de ter os seguintes requisitos:<br>
  - [x] Docker instalado;
  - [x] Docker Compose instalado;
  - [x] Nenhuma aplicação na porta **80**;
  - [x] Dar comandos como root, ou o usuário adcionado ao grupo Docker;
 
 <br><br>
 
 **2- Comandos**<br>
 Clone o projeto para algum diretório, lembrando que todo conteúdo já irá para uma pasta '/Dezconto'.
 <br> Feito isso, acesse a pasta utilizando o terminal, certifique-se que o caminho final seja algo parecido com,
  > .../Dezconto
  <br>
 
 Dê o comando **docker-compose up**.
  > Building dezconto
   > Step 1/5 : FROM node<br>
    ---> aa3e171e4e95<br>
    Step 2/5 : LABEL Ezequiel F<br>
    ---> Using cache<br>
    ---> 9ce6759b45d8<br>
    Step 3/5 : EXPOSE 8080<br>
    ---> Running in 3431a4f136c1<br>
    **...**
    
 <br><br>
 O container estará rodando, para ter certeza, apenas digite no terminal **docker ps**.
 Agora acesse algum dos seguintes endereços.<br>
  * [http://locahost](http://localhost:80)
  * [http://127.0.0.1](http://127.0.0.1:80)
  * [http://0.0.0.0](https://0.0.0.0:80)
  
  <br>
  
Apartir de agora, basta acessar via **Gmail** para ter seu cadastro automaticamente.

### 6.ACESSAR A APLICAÇÃO MOBILE<br><br>
Para isso, será necessário que faça o download do apk e instala-lo em seu dispositivo.
[Link para download](https://www.dropbox.com/s/z2nygf9du4jburu/dezconto.rar?dl=0);

Apartir da instalação, basta se autenticar via **Gmail** ou realizando um cadastro manual.
