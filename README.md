# acme-store Project
[![MUI](https://www.muicss.com/static/favicons/icon-192x192.png)](https://www.muicss.com/)
[![Angular](https://angular.io/assets/images/favicons/favicon-194x194.png)](https://angular.io/)
<a href="https://quarkus.io"><img src="https://design.jboss.org/quarkus/logo/final/PNG/quarkus_icon_rgb_256px_default.png" style="height: 192px;"></a>

This project is built with:

MUI, a lightweight CSS framework that follows Google's Material Design guidelines

Angular, the modern web developer's platform

Quarkus, the Supersonic Subatomic Java Framework

If you want to learn more about MUI, please visit its website: https://www.muicss.com/ .

If you want to learn more about Angular, please visit its website: https://angular.io/ .

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Requirements

Java 11
Maven 3.8.1

## Downloading the application

The application can be downloaded from github with the following command:
```shell script
git clone https://github.com/jefrog1844/acme-store.git
```

## Configuration (optional)
For standalone, development mode, Angular is configured with a proxy to forward http calls to the backend server.
The backend server is configured to run on port 8081.  If this port is not available, both the front end and backend need the port re-configured.
Both frontend and backend also have configuration files that can be changed.  Configuration files are:

**Angular:**
Edit the `http.proxy.json` file to match the port of the backend quarkus server.
Edit the `environment.ts` and `environment.prod.ts` to configure the backend rest urls

**Quarkus:**
Edit the `application.properties` file to match the port on which the backend server will run.
Edit the `application.properties` file to to edit any application configurable parameters.
Edit the `products.json` file to edit any product informtion.
Edit the `customers.json` file to edit any customer information.

## Building the User Interface (optional)

The Angular front end user interface is already compiled and packaged with the Quarkus microservice.
If however, you choose to make any changes to the front end interface, it will need recompiled and distributed to the backend service.
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

**Test with Angular:**
The front end unit tests can be exectured with the following command:
```shell script
ng test
```

Note: In order to run Angular unit tests, first follow the instructions for installing NPM

### Using the application

Add products to the cart by clicking the 'ADD TO CART' button on any product card

Clicking the 'CHECKOUT ... ITEMS' button will clear the cart and generate a receipt




