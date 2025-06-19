package com.acme.store.business.cart.boundary;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
