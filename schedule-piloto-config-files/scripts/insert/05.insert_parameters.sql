INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'URL con la direccion de la app web', FALSE, 'url.app.web', 'http://localhost:4200'); -- http://acquaboard.ml
INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'URL con el end point de los servicios api', FALSE, 'url.api.web', 'http://localhost:8080/api'); -- http://acquaboard.ml:8082/api
INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'Numero de intentos antes de bloquear el usuario', FALSE, 'limit.failed.authentication', '3');
INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'Numero de meses antes de expirar password', FALSE, 'password.expiration', '1');
INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'URL de destino despues de la confirmacion del email', FALSE, 'mail.confirmation.destination.url', 'http://localhost:4200/#/login'); -- http://acquaboard.ml/#/login

-- Email Creation User
INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'Mensaje corto para enviar email de registro de usuario', FALSE, 'email.register.user.short-message', 'QXByZWNpYWRvKGEpIHVzdWFyaW8sIGdyYWNpYXMgcG9yIHJlZ2lzdHJhcnNlIGVuIE15dmVudG9yeSwgbG8gaW52aXRhbW9zIGEgcXVlIHZlcmlmaXF1ZSBzdSBjdWVudGEu');
INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'Contenido para enviar email de registro de usuario', FALSE, 'email.register.user.message', 'PHA+QXByZWNpYWRvKGEpIFtVU0VSX05BTUVdIGdyYWNpYXMgcG9yIHJlZ2lzdHJhcnNlIGVuIE15dmVudG9yeSwgbG8gaW52aXRhbW9zIGEgcXVlIHZlcmlmaXF1ZSBzdSBjdWVudGEgYSB0cmF2ZXMgZGVsIHNpZ3VpZW50ZSBlbmxhY2UuPC9wPjxicj48YSB0YXJnZXQ9Il9ibGFuayIgaHJlZj0iW1VSTF9WRVJJRklDQVRJT05dIj5WZXJpZmljYXIgQ3VlbnRhPC9hPg==');

-- Email Creation User Us
INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'Mensaje corto para notificar el registor de un nuevo usuario', FALSE, 'email.register.user.short-message.us', 'SGVtb3MgcmVjaWJpZG8gZWwgcmVnaXN0cm8gZGUgdW4gbnVldm8gdXN1YXJpbyBlbiBudWVzdHJhIHBsYXRhZm9ybWEu');
INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'Contenido para notificar el registor de un nuevo usuario', FALSE, 'email.register.user.message.us', 'PHA+SGVtb3MgcmVjaWJpZG8gZWwgcmVnaXN0cm8gZGUgdW4gbnVldm8gdXN1YXJpby48L3A+PHA+PHN0cm9uZz5Vc3VhcmlvOiA8L3N0cm9uZz5bVVNFUl9OQU1FXTwvcD48cD48c3Ryb25nPkVtYWlsOiA8L3N0cm9uZz5bVVNFUl9FTUFJTF08L3A+');

-- Email Verification user
INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'Mensaje corto para notificar la verificacion de un nuevo usuario', FALSE, 'email.verification.user.short-message', 'U3UgY3VlbnRhIHNlIGhhIHZlcmlmaWNhZG8gY29ycmVjdGFtZW50ZS4=');
INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'Contenido para notificar la verificacion de un nuevo usuario', FALSE, 'email.verification.user.message', 'PHA+QXByZWNpYWRvKGEpIFtVU0VSX05BTUVdIHN1IGN1ZW50YSBzZSBoYSB2ZXJpZmljYWRvIGNvcnJlY3RhbWVudGUuIExvIGludml0YW1vcyBhIHF1ZSBpbmljaWUgc2VzacOzbiBhIHRyYXZlcyBkZWwgc2lndWllbnRlIGxpbmsuPC9wPjxhIHRhcmdldD0iX2JsYW5rIiBocmVmPSJbVVJMX0xPR0lOXSI+SW5pY2lhciBTZXNpb248L2E+');

-- Email Forgot Password
INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'Mensaje corto para notificar la restauracion de una contraseña', FALSE, 'email.forgot-password.user.short-message', 'QXByZWNpYWRvKGEpIHVzdWFyaW8sIHN1IGNvbnRyYXNlw7FhIGhhIHNpZG8gcmVzdGF1cmFkYSBjb3JyZWN0YW1lbnRlLg==');
INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'Contenido para notificar la restauracion de una contraseña', FALSE, 'email.forgot-password.user.message', 'PHA+QXByZWNpYWRvKGEpIFtVU0VSX05BTUVdIHN1IGNvbnRyYXNlw7FhIGhhIHNpZG8gcmVzdGF1cmFkYSBjb3JyZWN0YW1lbnRlLjwvcD48cD48c3Ryb25nPkNvbnRyYXNlw7FhOiA8L3N0cm9uZz5bVVNFUl9QQVNTV09SRF08L3A+');

-- Email Change Password
INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'Mensaje corto para notificar el cambio de una contraseña', FALSE, 'email.change-password.user.short-message', 'QXByZWNpYWRvKGEpIHVzdWFyaW8sIHN1IGNvbnRyYXNlw7FhIGhhIHNpZG8gY2FtYmlhZGEgY29ycmVjdGFtZW50ZS4=');
INSERT INTO PARAMETER(created_by, created_date, description, encrypted, name, value)
VALUES('default_script', now(), 'Contenido para notificar el cambio de una contraseña', FALSE, 'email.change-password.user.message', 'PHA+QXByZWNpYWRvKGEpIFtVU0VSX05BTUVdIHN1IGNvbnRyYXNlw7FhIGhhIHNpZG8gY2FtYmlhZGEgY29ycmVjdGFtZW50ZS48L3A+');
