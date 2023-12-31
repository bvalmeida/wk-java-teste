# WK-Java-Teste  
Teste realizado para a empresa WK em Java, Spring e Angular 2+  

# Pré-Requisitos  
1 - Tenha o git instalado em sua máquina, com as devidas configurações de usuário  
2 - Tenha o Docker e Docker Compose instalado  

# Iniciando o projeto  
1 - Clone o Projeto para um diretório local  

```
git clone  https://github.com/bvalmeida/wk-java-teste.git
```
  
2 - Entre no diretório onde se encontra o projeto  
3 - Com o terminal aberto, dentro do local onde se encontra o projeto, utilize o seguinte comando:  
  
```
docker-compose up --build
```

4 - Caso não deseje visualizar os logs da aplicação sendo levantada, utilize:  

```
docker-compose up --build -d
```

4 - Esse comando irá inicializar a api backend, o frontend e o banco de dados em containers Docker  
5 - A aplicação estará disponível acessando: http://localhost:4200  
