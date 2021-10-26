package com.acme.store.business.cart.boundary;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.acme.store.business.cart.entity.Receipt;
import com.acme.store.business.store.entity.LineItem;

@ApplicationScoped
@Path("cart")
public class CartResource {

    @Inject
    CartFacade facade;
    
    @GET
    @Path("{customerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCart(@PathParam("customerId") String customerId) {
        List<LineItem> cart = facade.getCart(customerId);
        return Response.ok(cart).build();
    }

    @PUT
    @Path("{customerId}/checkout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkout(@PathParam("customerId") String customerId) {
        Receipt receipt = facade.checkout(customerId);
        return Response.ok(receipt.print()).build();
    }

    @GET
    @Path("{customerId}/receipt")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response receipt(@PathParam("customerId") String customerId) {
        Receipt receipt = facade.checkout(customerId);
        return Response.ok(receipt).build();
    }
}
