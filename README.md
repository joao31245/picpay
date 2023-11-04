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
![SES1](https://github.com/joao31245/picpay/assets/134329276/90aed305-43d7-4640-b504-b861138b0221)
```
Criar nova identidade:
```
![Ir até identidades verificadas](https://github.com/joao31245/picpay/assets/134329276/8d9a083f-2c3b-4409-97e3-2d7dc3fba432)
![criar identidade](https://github.com/joao31245/picpay/assets/134329276/cb0379c6-be48-4f0b-8804-9b633ab77ec2)
Apos criar a identidade, confirme por e-mail.

Passo 2:

```
Configurar IAM:
```

```
Pesqiosar IAM:
```

![Pesquisar IAM](https://github.com/joao31245/picpay/assets/134329276/d2f6dc72-52a9-40b5-82c9-de2cba5eec82)

```
Vá até usuários
```

![Vá até usuários](https://github.com/joao31245/picpay/assets/134329276/2c5e97ac-7551-477b-b659-fb1463e179d8)

```
Crie um novo usuário
```

![Criar usuários](https://github.com/joao31245/picpay/assets/134329276/94315dc8-0edc-43c4-af0a-363db937113e)

```
Anexar politicas diretamente
```

![anexar politicas diretamente](https://github.com/joao31245/picpay/assets/134329276/1a35b48e-146f-4a38-8815-b7edbcd512b6)

```
Adicionar politicas do ses
```

![Selecionar politicas do ses](https://github.com/joao31245/picpay/assets/134329276/505e7ca1-d0c7-407a-8582-db749879e180)

```
Selecione o usuário criado, vá em credenciais de segurança e clique em criar chave de acesso:
```
![Criar chave de acesso](https://github.com/joao31245/picpay/assets/134329276/6ca9b063-b92d-4fe9-9a35-c9a9fdd818f2)

```
Selecione esse tipo para chave de acesso:
```

![Selecionar tipo de chave de acesso](https://github.com/joao31245/picpay/assets/134329276/fdfc7c18-ce9c-43e5-87f0-4f468b33455e)

Após criar a identidade e a chave de acesso será necessário passar elas pro código.

Passo 3:

```
Primeiro nas propiedades:
```
Passe as credenciais do seu usuário para o codigo no arquivo application.propeties.
![Passe as credenciais no código](https://github.com/joao31245/picpay/assets/134329276/ec77fb6f-94ad-4ede-8e89-a25cdac08c68)

```
Agora no código
```
No pacote infra selecione o pacote ses e a classe SesConfig e então mude o e-mail para o email da identidade cadastrada.
![Mude o email](https://github.com/joao31245/picpay/assets/134329276/cead5c14-c0ef-406c-b77c-0e4ec4d81f45)


Como a conta do ses é gratuita, você só pode enviar e-mail para identidades cadastradas.


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
