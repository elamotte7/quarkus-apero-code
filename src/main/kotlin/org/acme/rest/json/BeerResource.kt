package org.acme.rest.json

import com.fasterxml.jackson.annotation.JsonProperty
import org.acme.hibernate.panache.model.Beer
import javax.transaction.Status
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.GenericEntity
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider


@Path("/beers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class BeerResource {

    @GET
    fun all(): Response {
        val entity = object : GenericEntity<List<Beer>>(Beer.listAll()) {}
        return Response.ok(entity).build()
    }

    @POST
    @Transactional
    fun add(beer: Beer): Response {
        beer.persist()
        return Response.ok(beer).status(Response.Status.CREATED).build()

    }

    @PUT
    @Path("{id}")
    @Transactional
    fun update(@PathParam("id") id: Int, beer: Beer): Response {
        val entity = Beer.findById<Beer>(id)
                ?: throw WebApplicationException("Beer with id of $id does not exist.", 404)
        Beer.update("update from BEER_TABLE set BEER_NAME = ?1, BEER_COLOR = ?2, TASTED = ?3 where id = ?4",
                beer.name, beer.color, beer.tasted, entity.id)

        return Response.ok(beer).build()
    }

    @PATCH
    @Path("{id}")
    @Transactional
    fun patch(@PathParam("id") id: Int, beer: Beer): Response {
        val entity = Beer.findById<Beer>(id)
                ?: throw WebApplicationException("Beer with id of $id does not exist.", 404)
        Beer.update("update from BEER_TABLE set BEER_NAME = ?1, BEER_COLOR = ?2, TASTED = ?3 where id = ?4",
                beer.name, beer.color, beer.tasted, entity.id)

        return Response.ok(beer).build()
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    fun delete(@PathParam("id") id: Int) : Response{
        val entity = Beer.findById<Beer>(id)
                ?: throw WebApplicationException("Beer with id of $id does not exist.", 404)
        entity.delete()
        return Response.status(Response.Status.NO_CONTENT).build();

    }

    @DELETE
    @Transactional
    fun deleteCompleted() : Response{
        Beer.delete("tasted", true)
        return Response.status(Response.Status.NO_CONTENT).build();

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