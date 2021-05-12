# microservicios-udemy

#Para obtener token Autorizacion

POST http://localhost:8081/api/oauth-ms/oauth/token

Authorization:
--------------
Basic Auth
Username: martindex.app
Password: martindex.secret

Body - x-www-form-urlencoded
--------
Key: Value
username: admin
password: 123456
grant_type: password

# Refrescar token

POST http://localhost:8081/api/oauth-ms/oauth/token

Authorization:
--------------
Basic Auth
Username: martindex.app
Password: martindex.secret

Body - x-www-form-urlencoded
--------
Key: Value
grant_type: refresh_token
refresh_token: <token del refresh>
