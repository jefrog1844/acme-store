package com.acme.store.business.store.boundary;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.acme.store.business.store.entity.PurchaseRequest;

@ApplicationScoped
@Path("store")
public class StoreResource {

    @Inject
    StoreFacade facade;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addToCart(@Valid PurchaseRequest purchaseRequest) {
        facade.addToCart(purchaseRequest);
        return Response.ok().status(Status.ACCEPTED).build();
    }
}
