# PRÁCTICA CONTENEDORES 

# Dev containers
Para levantar el docker-compose de desarrollo, estando en la raiz del proyecto lanzar:

```sh
docker-compose -f dev.docker-compose.yml up -d
```

Una vez levantados los servicios del docker-compose (MongoDB, MysqlDB y RabbitMQ), ejecutar cada dev container. Arrancar los servicios por orden de la siguiente forma:

1º Weather service
```sh
node src/server.js
```

2º Planner
```sh
mvn spring-boot:run
```

3º TopoService
```sh
mvn spring-boot:run
```

4º Server
```sh
node src/server.js
```

Una vez levantados los dev containers puedes conectarte a localhost:3000 para utilizar la aplicación.

Los dev containers tienen configuradas unas variables de entorno en el "devcontainer.json" para su correcto funcionamiento.

# Script bash
El script "buildImages.sh" compila y pushea a dockerHub todas las imagenes de los servicios. Se puede añadir un parametro como nombre de la cuenta de dockerHub, sino por defecto es rsotosan (mi cuenta).

# Docker-compose
El docker compose levanta todos los servicios y tiene configurados todas las variables de entorno y conexiones entre servicios. Basta con levantar el docker-compose "docker-compose.yml" y entrar en localhost:3000. El puerto 3000 es el único que se bindea hacia fuera.

Los datos de la base de datos de MYSQL se persisten en una carpeta en la raiz del proyecto llamada "mysqldata" y los datos de la base de datos Mongo se persisten en la carpeta "mongodata".