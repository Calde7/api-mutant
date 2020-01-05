Mutant
====

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Mens.
Se necesita detectar si un humano es mutante basándose en su secuencia de ADN.
En donde se recibirá como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN.
Un humano es mutante, si se encuentra ​ más de una secuencia de cuatro letras iguales​, de forma oblicua, horizontal o vertical.

### Development

Mutant API
====

## Local Development
* Maven 3.5
* Java 8
* Spring Boot: https://spring.io/projects/spring-boot
* MySQL Database

# Check out repository

```bash
git clone https://github.com/Calde7/api-mutant.git
```

## Installation
Run `mvn clean install`.

## Deploying
Run `MutantApplication` from your favorite IDE. Configure your application.properties about your profile. Run `mvn spring-boot:run` to start the service. To verify your deployment,
issue a GET to localhost:8080/stats This find out statistics.

## Endpoints:

- POST: http://157.230.239.134:8080/api-mutant/mutant

- GET: http://157.230.239.134:8080/api-mutant/stats

Body Example:
{
"dna":["TCCAGG","CTCAGG","CGACGT","ACGTCG","ATCGTC","TCACGA"]
}
