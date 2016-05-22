package com.goda5.sample.spring;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by tong on 22/05/2016.
 */
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class OrderResource {

    @PersistenceContext(unitName = "books")
    private EntityManager em;

    @GET
    @Path("/{id}")
    public Response getOrder(@PathParam("id") Integer id) {

        BookOrder o = em.find(BookOrder.class, id);

        if (o == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(o).build();
    }

    class BookOrder {

    }
}

