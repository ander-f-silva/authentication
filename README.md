# Solução do Teste

Para realizar a solução foram realizada as seguintes tecnologias:

*Spring Data (Hibernate versão mais atual 5.0.3Concrete);
*Spring Boot com Tomcat;
*Jwt para gerar token.
*Java versão 1.8

O sistema esta publicado no Heroku

Segue os endpoints do serviço:

*Restfull para realizar o cadastro do usuário

https://authentication-01.herokuapp.com/user

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

*Restfull para autenticar no sistema

https://authentication-01.herokuapp.com?email={email}&password={passoword}

Método usado é GET

*Restfull para recuperar o pefil

https://authentication-01.herokuapp.com/profile/user/1

Método usado é GET

Observação No header informar a propriedade token com o valor

