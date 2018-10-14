# Serviço para realizar autenticação

Para realizar a solução foram realizada as seguintes tecnologias:

* Spring Data (Hibernate versão atualizado para versão 5.2.17);
* Spring Boot com Tomcat - atualizado para versão 2.0.5;
* Jwt para gerar token.
* Java versão 1.8 - JDK Oracle


Segue os endpoints do serviço:

## Cadastro

* Restfull para realizar o cadastro do usuário

http://localhost:8080/users

Método usado é POST

```
{
  "name": "João da Silva",
  "email": "joao3@silva2.io",
  "password": "1234",
  "phones": [
    {
      "number": "987654321",
      "ddd": "21"
    }
  ]
}
```
## Login

* Endpoint para autenticar no sistema

http://localhost:8080/login

Método usado é POST

```
{
    "email":"joao3@silva2.io",
    "password":"1234"
}
```

## Perfil

* Endpoint para recuperar o pefil

http://localhost:8080/profiles/users/{id}

Método usado é GET

Observação No header informar a propriedade token com o valor

