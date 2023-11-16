# Aplicación de creación de usuarios

### Documentación de referencia

La aplicación consiste en un servicio spring boot que escribe datos en una base de datos en memoria H2 para las tablas User y Phone de acuerdo a la siguiente estructura:
```json
{
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "hunter2",
"phones": [
    {
    "number": "1234567",
    "citycode": "1",
    "contrycode": "57"
    }
]
}
```

La estructura de la aplicación se puede visualizar en el siguiente diagrama:

![](src/main/resources/static/create-users.png)

### Guia de instalación y uso

Para iniciar la aplicación se requiere contar con Java 17 instalado localmente para su ejecución. Una vez instalado clonar repositorio y ejecutar:

### Linux y Mac
```bash
./mvnw spring-boot:run
```
### Windows
```bash
mvnw spring-boot:run
```

Para consumir las api de creación de usuarios se requiere contar con un usuario ya autenticado. Para efectos de pruebas se realizó el registro de un usuario inicial, el que cuenta con un token jwt sin fecha de expiración para efectos de pruebas.
La documentación de las api se puede visualizar en la ruta local http://localhost:8070/swagger-ui/ , de todos modos a continuación se deja el cUrl para consumir cada endpoint de la api:

* Para visualizar todos los usuarios:
```
  curl -X GET --location "http://localhost:8080/v1/users" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Ikp1YW4gUGVyZXoiLCJzdWIiOiJKdWFuIFBlcmV6IiwiaWF0IjoxNzAwMDUyMzU3fQ.l74YUO2kKG8y6bHJmH9YI7IXuBxuKj9aCCPbVRqCyHw"
```
* Para registro de un usuario de prueba:
```
curl -X POST --location "http://localhost:8080/v1/users" \
-H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Ikp1YW4gUGVyZXoiLCJzdWIiOiJKdWFuIFBlcmV6IiwiaWF0IjoxNzAwMDUyMzU3fQ.l74YUO2kKG8y6bHJmH9YI7IXuBxuKj9aCCPbVRqCyHw" \
-H "Content-Type: application/json" \
-d "{
\"name\": \"Juan Rodrigue\",
\"email\": \"user.name@domain.com\",
\"password\": \"Geeks@portal20\",
\"phones\": [
{
\"number\": \"1234568\",
\"citycode\": \"1\",
\"countrycode\": \"57\"
}
]
}"
```

* Para modificación de un usuario de prueba (se debe modificar el campo REPLACE_ID por uno de los id ya registros, ver sección "Para visualizar todos los usuarios"):

```
curl -X PUT --location "http://localhost:8080/v1/users/REPLACE_ID" \
    -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Ikp1YW4gUGVyZXoiLCJzdWIiOiJKdWFuIFBlcmV6IiwiaWF0IjoxNzAwMDUyMzU3fQ.l74YUO2kKG8y6bHJmH9YI7IXuBxuKj9aCCPbVRqCyHw" \
    -H "Content-Type: application/json" \
    -d "{
          \"name\": \"Juan Perez\",
          \"email\": \"juan@perez.org\",
          \"password\": \"hunter3\",
          \"phones\": [
            {
              \"number\": \"1234566\",
              \"citycode\": \"1\",
              \"countrycode\": \"55\"
            }
          ]
        }"
```

