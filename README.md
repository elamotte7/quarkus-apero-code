# quarkus-apero-code project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```bash
$ ./mvnw quarkus:dev
```

## Running the application in production mode

You can run your application in productoin mode 
Firstable youhave to launch the MariaDB : 
```bash
$ docker-compose -f src/main/dockercompose/docker-compose-mariadb.yml up
```
 the launch the app in JVM mode or native mode 
 
 JVM
```bash
$ java -jar target/quarkus-apero-code-1.0-SNAPSHOT-runner.jar
```

Native (see below how to generate the native app)
```bash
$ ./target/rest-json-quickstart-1.0-SNAPSHOT-runner
```

Then you can acces to the GUI via http://localhost:8080

Or access directly to the beer api vi http://localhost:8080/beers

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `rest-json-quickstart-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/rest-json-quickstart-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw clean package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/rest-json-quickstart-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.