package org.acme.rest.json

import com.fasterxml.jackson.annotation.JsonProperty
import org.acme.hibernate.panache.model.Fruit
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.GenericEntity
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider


@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class FruitResource {

    @GET
    fun getFruits(): Response {
        val entity = object : GenericEntity<List<Fruit>>(Fruit.listAll()) {}
        return Response.ok(entity).build()
    }

    @POST
    @Transactional
    fun addFruit(fruit: Fruit): Response {
        fruit.persist()
        return Response.ok(fruit).status(Response.Status.CREATED).build()

    }

    @PUT
    @Path("{id}")
    @Transactional
    fun update(@PathParam("id") id: Int, fruit: Fruit): Fruit? {
        if (fruit == null) {
            throw WebApplicationException("Fruit was not set on request.", 422)
        }
        val entity = Fruit.findById<Fruit>(id)
                ?: throw WebApplicationException("Fruit with id of $id does not exist.", 404)
        Fruit.update("update from FRUIT_TABLE set FRUIT_NAME = ?1, FRUIT_COLOR = ?2 where id = ?3",
                fruit.name, fruit.color, entity.id)

        return fruit
    }
}

@Provider
class ErrorMapper : ExceptionMapper<Exception?> {
    override fun toResponse(exception: Exception?): Response {
        var code = Response.Status.INTERNAL_SERVER_ERROR.statusCode
        if (exception is WebApplicationException) {
            code = exception.response.status
        }
        return Response.status(code)
                .entity(ErrorResponse(code, exception?.message)).build()
    }
}

data class ErrorResponse(
        @JsonProperty("error-code") val errorCode: Int,
        @JsonProperty("error") val error: String?

)