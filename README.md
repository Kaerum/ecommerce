Rodando o servidor:  
Navegue até a pasta out
```
cd out
java -jar ecommerce-server.jar
```
O servidor deverá abrir na porta **8080**, isso é importante caso não seja dá erro de CORS.

Caso queira rodar direto no fonte:  
O projeto usa Maven, basta chamar o comando
```
cd servidor
mvn spring-boot:run

ou

mvnw spring-boot:run
```

Rodando o frontend:
Navegue até a pasta out e rode o frontend.exe, é um binário deno pré compilado
```
cd out
./frontend.exe
```

Caso queira rodar o front direto no fonte:  
Navegue até a pasta webclient/ecommerce-client
```
cd webclient/ecommerce-client
pnpm install

ou

npm install

ou 

yarn install


npm run dev
```

O front end deve estar hosteado na porta **3000**, isso é importante pois caso não seja dá erro de CORS.