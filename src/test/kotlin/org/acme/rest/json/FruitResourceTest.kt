package org.acme.rest.json

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class FruitResourceTest {

    @Test
    fun testGetFruits() {
        given()
                .`when`().get("/fruits")
                .then()
                .statusCode(200)
                .body(`is`("[{\"id\":1,\"name\":\"apple\",\"color\":\"red\"},{\"id\":2,\"name\":\"pineapple\",\"color\":\"greenish-yellow\"},{\"id\":3,\"name\":\"banana\",\"color\":\"yellow\"},{\"id\":4,\"name\":\"strawberry\",\"color\":\"red\"},{\"id\":5,\"name\":\"pear\",\"color\":\"yellowish color\"}]") )
    }

}