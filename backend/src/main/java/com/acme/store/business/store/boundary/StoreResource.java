package com.acme.store.business.store.boundary;

import java.util.Collection;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import com.acme.store.business.store.entity.LineItem;
import com.acme.store.business.store.entity.Product;
import com.acme.store.business.store.entity.PurchaseRequest;

@ApplicationScoped
@Path("store")
public class StoreResource {

    @Inject
    StoreFacade facade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductList() {
        Collection<Product> products = facade.getProducts().values();
        return Response.ok(products).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addToCart(@Valid PurchaseRequest purchaseRequest) {
        LineItem li = facade.addToCart(purchaseRequest);
        return Response.ok(li).status(Status.ACCEPTED).build();
    }
}
