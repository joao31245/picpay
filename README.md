# PicPay Simplificado

Desafio do picpay, para fazer um picpay simplificado com dois tipos de usuários e com transações.

## 🚀 Começando

Para conseguir uma cópia do projeto somente será necessário clicar em code e download zip.

### 📋 Pré-requisitos

```
JDK 19 Instalada.
```
```
PostgreSQL com um banco de dados criado com o nome de: picpay,
```
```
IDE de preferencia IntelliJ
```
```
Conta no aws.
```
```
ses configuraado(aws).
```

### 🔧 Instalação

Passo 1:
```
Configurar ses:

```
```
Pesquise o serviço:
```
<img src="https://github.com/joao31245/picpay/assets/134329276/a7ac972d-74e1-4c2a-9e69-f8b330fc65ff" />


```
Criar nova identidade:
```
![Ir até identidades verificadas](https://github.com/joao31245/picpay/assets/134329276/59128d73-0033-4571-9615-f2fbbf494269)

<img src="https://github.com/joao31245/picpay/assets/134329276/73c7b4ef-4b59-4680-ba75-2d778c110268" />
Apos criar a identidade, confirme por e-mail.
Passo 2:

```
Configurar IAM:
```

```
Pesqiosar IAM:
```

![Pesquisar IAM](https://github.com/joao31245/picpay/assets/134329276/20dd7ac6-b9ff-470e-b6bc-70ebbadc707b)

```
Vá até usuários
```
![Vá até usuários](https://github.com/joao31245/picpay/assets/134329276/7fa90164-82ea-4ad8-9f4e-db4547b0294f)

```
Crie um novo usuário
```

![Criar usuários](https://github.com/joao31245/picpay/assets/134329276/ce3e3338-c246-431e-9419-88b9789dea1e)


```
Anexar politicas diretamente
```

![anexar politicas diretamente](https://github.com/joao31245/picpay/assets/134329276/8e1fd958-dc95-4f31-be33-b0a2d38b34f8)


```
Adicionar politicas do ses
```

![Selecionar politicas do ses](https://github.com/joao31245/picpay/assets/134329276/d85bcb6c-ce93-4200-9766-bac557a7b173)


```
Selecione o usuário criado, vá em credenciais de segurança e clique em criar chave de acesso:
```

![Criar chave de acesso](https://github.com/joao31245/picpay/assets/134329276/5afac4b3-1021-465b-b407-115c818f9a05)

```
Selecione esse tipo para chave de acesso:
```

![Selecionar tipo de chave de acesso](https://github.com/joao31245/picpay/assets/134329276/a39a5e93-cb68-4b64-951f-374e5684c904)


Após criar a identidade e a chave de acesso será necessário passar elas pro código.

Passo 3:

```
Primeiro nas propiedades:
```
Passe as credenciais do seu usuário para o codigo no arquivo application.propeties.

![Passe as credenciais no código](https://github.com/joao31245/picpay/assets/134329276/d2de6b48-f08e-44ee-8689-b04d44d3ace9)

```
Agora no código
```

No pacote infra selecione o pacote ses e a classe SesConfig e então mude o e-mail para o email da identidade cadastrada.

![Mude o email](https://github.com/joao31245/picpay/assets/134329276/e131d0e2-0d08-4343-89fc-0d216c5e15b2)


Como a conta do ses é gratuita, você só pode enviar e-mail para identidades cadastradas.
## Como usar

### Controller user
A aplicação contem dois usuários, sendo um deles comum(COMMON) e outro lojista(MERCHANT). O usuário lojista somente pode receber transações,
em contraparte o usuário comum pode rececber e fazer transações.
É necessário passar como parametro o tipo do usuário ao fazer o metodo post, tendo em mente que somente um deles pode realizar transações.

```
Metodo Get : http://localhost:8080/user
```
![ComoUsarGetUser](https://github.com/joao31245/email-service/assets/134329276/48bef09c-033d-4050-8949-16966abb43f6)

```
Metodo Post : http://localhost:8080/user
```
![ComoUsarPostUser](https://github.com/joao31245/email-service/assets/134329276/dcccc549-167d-45c5-bebd-6ce405998233)

### Controller transaction
```
Metodo Post : http://localhost:8080/user
```
![ComoUsarPostTransaction](https://github.com/joao31245/email-service/assets/134329276/903bdaff-aa4a-4fef-907b-525a846c2857)

Esses são os principais metodos, porém o sistema implementa todo o crud básico. Para mais detalhes, ao rodar a API, consulte: http://localhost:8080/swagger-ui/index.html#

## 🛠️ Construído com

* [Spring Boot](https://spring.io/projects/spring-boot) - O framework web usado
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [AWS](https://rometools.github.io/rome/) - Para utilizar o serviço de E-mails.


## ✒️ Autores

* **João Carlos Machado Filho** - *Trabalho Inteiro* - [João Carlos](https://github.com/joao31245)


## 📄 Licença
Sob Open Software License v3.0.
---
⌨️ com ❤️ por [João Carlos](https://github.com/joao31245) 😊
