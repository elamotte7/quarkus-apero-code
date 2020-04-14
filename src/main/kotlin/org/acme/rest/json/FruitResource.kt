package org.acme.rest.json

import org.acme.hibernate.panache.model.Fruit
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/fruits")
class FruitResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getFruits(): List<Fruit> = Fruit.listAll()
}