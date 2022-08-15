*******Servicio BCI***************
Al ejecutar la aplicación se levantan 3 servicios:

http://localhost:8080/public/create
http://localhost:8080/public/all
http://localhost:8080/user/all


##########Creando usuario

Para crear un usuario desde la consola se ejecuta la siguiente linea. 

curl -X POST http://localhost:8080/public/create -H 'Content-Type: application/json' -d '{
  "id": "555",
  "name": "jhonnathan cardona",
  "email": "admin@bci.com",
  "password": "secreto",
  "phones": [
    {
      "number": "1234",
      "citycode": "code",
      "contrycode": "code",
      "user": null
    }
  ]
}'


Respuesta esperada
{
  "id": "8f6c188f-9e4d-444d-b567-4ee8c0df7c79",
  "mensaje": "ok",
  "created": "2022/08/14 22:40:34",
  "modified": "2022/08/14 22:40:34",
  "last_login": null,
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBiY2kuY29tIiwiZXhwIjoxNjYwNTYzNjM0LCJpYXQiOjE2NjA1MzQ4MzQsInJvbCI6eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn19.8oHVYDAIL2gz2J9asTkxpBSAY6sKlJsdrpZVnkAq2uo",
  "isactive": false
}

############Visualizando todos los usuarios

Si el usuario se creo con el correo admin@bci.com, automaticamente se le agrega el rol de Admin y a través del token, podrá acceder al servicio de la siguiente manera:

curl -H  "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBiY2kuY29tIiwiZXhwIjoxNjYwNTYzNjM0LCJpYXQiOjE2NjA1MzQ4MzQsInJvbCI6eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn19.8oHVYDAIL2gz2J9asTkxpBSAY6sKlJsdrpZVnkAq2uo" curl http://localhost:8080/user/all

Respuesta esperada
[
  {
    "id": "8f6c188f-9e4d-444d-b567-4ee8c0df7c79",
    "name": "jhonnathan cardona",
    "email": "admin@bci.com",
    "password": "secreto",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBiY2kuY29tIiwiZXhwIjoxNjYwNTYzNjM0LCJpYXQiOjE2NjA1MzQ4MzQsInJvbCI6eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn19.8oHVYDAIL2gz2J9asTkxpBSAY6sKlJsdrpZVnkAq2uo",
    "created": "2022/08/14 22:40:34",
    "modified": "2022/08/14 22:40:34",
    "phones": [
      
    ]
  }
]



