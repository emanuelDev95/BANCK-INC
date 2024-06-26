# Banck - Inc

## Descripción

Microservicio encargado de la gestión de tarjetas de crédito y débito de los clientes de Bank Inc.

## Tecnologías Utilizadas

- Java 17
- Maven
- Spring Boot 3
- Mysql

## Configuración del Entorno Local

1. Clona el repositorio: `git clone https://github.com/emanuelDev95/BANCK-INC`
2. Navega al directorio del proyecto: `cd BANK-INC`
3. Instala las dependencias: `mvn clean install`




6. Ejecuta el proyecto: `java  -jar target/banck-inc-0.0.1-SNAPSHOT.jar`



## Despliegue en la Nube

Descripción de cómo desplegar el proyecto en la nube. Esto variará dependiendo del proveedor de servicios en la nube que estés utilizando. para desplegarlo en un cluster de kubernetes :




## Reporte de prueba unitarias
![alt](/src/main/resources/images/cobertura.png)


## Uso

Haga uso de las siguientes Curl para probar el servicio, o puede usar la colección de Postman que se encuentra en la carpeta `resorce` del proyecto.




1. Obtener numero de tarjeta
```shell
curl --location 'http://localhost:8080/card/352822/number'
```
2. Activar Tarjeta
```shell
curl --location 'http://localhost:8080/card/enroll' \
--header 'Content-Type: application/json' \
--data '{
  "cardId": "3528223261062559"
}'
```


3. Bloquear tarjeta
```shell
curl --location --request DELETE 'http://localhost:8080/card/3471701733836460'
```
4. Recargar tarjeta
```shell
curl --location 'http://localhost:8080/card/balance' \
--header 'Content-Type: application/json' \
--data '{
  "cardId": "3528223261062559",
  "balance": "5000"
}'
```
5. Consultar saldo
```shell
curl --location 'http://localhost:8080/card/balance/3528223261062559'
```
6. Realizar compra
```shell
curl --location 'http://localhost:8080/transaction/purchase' \
--header 'Content-Type: application/json' \
--data '
{
    "cardNumber": "3528223261062559",
    "price":100 
}
'
```
7. Obtener compra
```shell
curl --location 'http://localhost:8080/transaction/2'
```
8. Anular compra
```shell
curl --location 'http://localhost:8080/transaction/anulation' \
--header 'Content-Type: application/json' \
--data '{
  "cardId": "3528223261062559",
  "transactionId": "2"
}'
```