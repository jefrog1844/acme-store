# acme-store Project
[![Angular](https://angular.io/assets/images/favicons/favicon-194x194.png)](https://angular.io/)
[![Quarkus](https://quarkus.io/assets/images/quarkus_logo_horizontal_rgb_reverse.svg)](https://quarkus.io/)

This project uses Quarkus, the Supersonic Subatomic Java Framework and Angular, the modern web developer's platform.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

If you want to learn more about Angular, please visit its website: https://angular.io/ .

## Downloading the application

The application can be downloaded from github with the following command:
```shell script
git clone https://github.com/jefrog1844/acme-store.git
```

## Building the User Interface (optional)

The Angular front end user interface is already compiled and packaged with the Quarkus microservice.
If however, you choose to make any changes to the front end interface, it will need recompiled and distirbuted to the backend service.
If changes are made, navigate to the frontend directory to execute build scripts.  The Angular application can now be built in two steps:

**Install with NPM:**
```shell script
npm install
```
**Build with ng:**
```shell script
ng build
```

## Packaging and running the Microservice

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

The application is available using the url `http://localhost:8081/`

### Running unit tests

**Test with Maven:**
The microservice can be unit tested with the following command:
```shell script
./mvnw test
```

Unit tests are also executed as part of the packaging process.

**Test with Karma:**
Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).




