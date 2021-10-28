package com.acme.store.business.store.boundary;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
