
package com.acme.store;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

import com.acme.store.business.cart.entity.Receipt;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MainTest {

        private static String storeUrl;
        private static String cartUrl;
        private static JsonObjectBuilder body;

        @BeforeAll
        public static void startTheServer() throws Exception {
                storeUrl = "http://localhost:8081/store";
                cartUrl = "http://localhost:8081/cart";
                body = Json.createObjectBuilder();
        }

        @BeforeEach
        public void newClient() {

        }

        @Test
        @Order(1)
        void testInput1() {

                body = Json.createObjectBuilder();
                body.add("sku", Long.valueOf(1));
                body.add("customerId", "1");

                given().contentType(MediaType.APPLICATION_JSON).body(body.build().toString()).post(storeUrl).then()
                                .statusCode(Status.ACCEPTED.getStatusCode());
        }

        @Test
        @Order(2)
        void testInput2() {

                body = Json.createObjectBuilder();
                body.add("sku", Long.valueOf(2));
                body.add("customerId", "1");

                given().contentType(MediaType.APPLICATION_JSON).body(body.build().toString()).post(storeUrl).then()
                                .statusCode(Status.ACCEPTED.getStatusCode());
        }

        @Test
        @Order(3)
        void testInput3() {

                body = Json.createObjectBuilder();
                body.add("sku", Long.valueOf(3));
                body.add("customerId", "1");

                given().contentType(MediaType.APPLICATION_JSON).body(body.build().toString()).post(storeUrl).then()
                                .statusCode(Status.ACCEPTED.getStatusCode());
        }

        @Test
        @Order(4)
        void testInput4() {

                body = Json.createObjectBuilder();
                body.add("sku", Long.valueOf(4));
                body.add("customerId", "2");

                given().contentType(MediaType.APPLICATION_JSON).body(body.build().toString()).post(storeUrl).then()
                                .statusCode(Status.ACCEPTED.getStatusCode());
        }

        @Test
        @Order(5)
        void testInput5() {

                body = Json.createObjectBuilder();
                body.add("sku", Long.valueOf(5));
                body.add("customerId", "2");

                given().contentType(MediaType.APPLICATION_JSON).body(body.build().toString()).post(storeUrl).then()
                                .statusCode(Status.ACCEPTED.getStatusCode());
        }

        @Test
        @Order(6)
        void testInput6() {

                body = Json.createObjectBuilder();
                body.add("sku", Long.valueOf(6));
                body.add("customerId", "3");

                given().contentType(MediaType.APPLICATION_JSON).body(body.build().toString()).post(storeUrl).then()
                                .statusCode(Status.ACCEPTED.getStatusCode());
        }

        @Test
        @Order(7)
        void testInput7() {

                body = Json.createObjectBuilder();
                body.add("sku", Long.valueOf(7));
                body.add("customerId", "3");

                given().contentType(MediaType.APPLICATION_JSON).body(body.build().toString()).post(storeUrl).then()
                                .statusCode(Status.ACCEPTED.getStatusCode());
        }

        @Test
        @Order(8)
        void testInput8() {

                body = Json.createObjectBuilder();
                body.add("sku", Long.valueOf(8));
                body.add("customerId", "3");

                given().contentType(MediaType.APPLICATION_JSON).body(body.build().toString()).post(storeUrl).then()
                                .statusCode(Status.ACCEPTED.getStatusCode());
        }

        @Test
        @Order(9)
        void testInput9() {

                body = Json.createObjectBuilder();
                body.add("sku", Long.valueOf(9));
                body.add("customerId", "3");

                given().contentType(MediaType.APPLICATION_JSON).body(body.build().toString()).post(storeUrl).then()
                                .statusCode(Status.ACCEPTED.getStatusCode());
        }

        @Test
        @Order(10)
        void testReceipt1() {

                Receipt receipt = given().contentType(MediaType.APPLICATION_JSON).body("")
                                .get(cartUrl + "/{customerId}/receipt", "1").then()
                                .statusCode(Status.OK.getStatusCode()).extract().as(Receipt.class);

                Assertions.assertEquals(0, receipt.totalCost().compareTo(BigDecimal.valueOf(29.83)));
                Assertions.assertEquals(0, receipt.totalTax().compareTo(BigDecimal.valueOf(1.50)));
                printReceipt(receipt);

        }

        @Test
        @Order(11)
        void testReceipt2() {

                Receipt receipt = given().contentType(MediaType.APPLICATION_JSON).body("")
                                .get(cartUrl + "/{customerId}/receipt", "2").then()
                                .statusCode(Status.OK.getStatusCode()).extract().as(Receipt.class);

                Assertions.assertEquals(0, receipt.totalCost().compareTo(BigDecimal.valueOf(65.15)));
                Assertions.assertEquals(0, receipt.totalTax().compareTo(BigDecimal.valueOf(7.65)));
                printReceipt(receipt);

        }

        @Test
        @Order(12)
        void testReceipt3() {

                Receipt receipt = given().contentType(MediaType.APPLICATION_JSON).body("")
                                .get(cartUrl + "/{customerId}/receipt", "3").then()
                                .statusCode(Status.OK.getStatusCode()).extract().as(Receipt.class);

                Assertions.assertEquals(0, receipt.totalCost().compareTo(BigDecimal.valueOf(74.68)));
                Assertions.assertEquals(0, receipt.totalTax().compareTo(BigDecimal.valueOf(6.70)));
                printReceipt(receipt);

        }

        private void printReceipt(Receipt receipt) {
                System.out.print(receipt.print());
                System.out.print(System.lineSeparator());
        }

        @AfterAll
        static void destroyClass() {

        }
}
