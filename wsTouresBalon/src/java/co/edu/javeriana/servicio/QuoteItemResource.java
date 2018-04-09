/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.servicio;

import co.edu.javeriana.objetos.consultas;
import java.math.BigDecimal;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Jesus Bayona
 */
@Path("QuoteItem")
public class QuoteItemResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of QuoteItemResource
     */
    public QuoteItemResource() {
    }

    /**
     * Retrieves representation of an instance of co.edu.javeriana.servicio.QuoteItemResource
     * @return an instance of java.lang.String
     
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of QuoteItemResource
     * @param content representation for the resource
     
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }*/
    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String addQuoteItems(@QueryParam("NUM_ID") BigDecimal NUM_ID,
                            @QueryParam("PRODUCT_ID") BigDecimal PRODUCT_ID,
                            @QueryParam("PRODUCT_NAME") String PRODUCT_NAME,
                            @QueryParam("PARTNUM") String PARTNUM,
                            @QueryParam("PRICE") BigDecimal PRICE,
                            @QueryParam("QUANTITY") BigDecimal QUANTITY,
                            @QueryParam("ORDER_ID") BigDecimal ORDER_ID) {
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        try {
            String resultado;
            consultas cons = new consultas();
            resultado = cons.creacionQuoteItems(NUM_ID, PRODUCT_ID, PRODUCT_NAME, PARTNUM, PRICE, QUANTITY, ORDER_ID);
            return resultado;
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
            return "Error";
        } 
    }
}
