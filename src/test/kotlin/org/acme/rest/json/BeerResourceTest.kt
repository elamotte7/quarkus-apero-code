package org.acme.rest.json

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@QuarkusTest
@TestMethodOrder(OrderAnnotation::class)
class BeerResourceTest {

    @Test
    @Order(1)
    fun testGetFruits() {
        given()
                .`when`().get("/beers")
                .then()
                .statusCode(200)
                .body(`is`("[{\"id\":1,\"name\":\"Chouffe\",\"color\":\"Yellow\",\"tasted\":false,\"order\":0},{\"id\":2,\"name\":\"Chimay\",\"color\":\"Amber\",\"tasted\":false,\"order\":0},{\"id\":3,\"name\":\"Kwack\",\"color\":\"Amber\",\"tasted\":false,\"order\":0},{\"id\":4,\"name\":\"Ch'ti Triple\",\"color\":\"Yellow\",\"tasted\":false,\"order\":0},{\"id\":5,\"name\":\"Goudale\",\"color\":\"Amber\",\"tasted\":false,\"order\":0}]"))
    }

    @Test
    @Order(2)
    fun testAddFruit() {
        var payload = "{\"name\":\"goudale\",\"color\":\"Amber\",\"tasted\":false,\"order\":0}"
        given()
                .contentType(ContentType.JSON)
                .`when`()
                .body(payload)
                .post("/beers")
                .then()
                .statusCode(201)
    }

}