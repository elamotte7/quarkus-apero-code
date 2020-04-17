package org.acme.rest.json

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class BeerResourceTest {

    @Test
    fun testGetFruits() {
        given()
                .`when`().get("/beers")
                .then()
                .statusCode(200)
                .body(`is`("[{\"id\":1,\"name\":\"Chouffe\",\"color\":\"Blond\"},{\"id\":2,\"name\":\"Chimay\",\"color\":\"Amber\"},{\"id\":3,\"name\":\"Kwack\",\"color\":\"Amber\"},{\"id\":4,\"name\":\"Ch'ti Triple\",\"color\":\"red\"},{\"id\":5,\"name\":\"Goudale\",\"color\":\"Amber\"}]") )
    }

}