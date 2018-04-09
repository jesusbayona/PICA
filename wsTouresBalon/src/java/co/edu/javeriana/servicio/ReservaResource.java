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
@Path("reserva")
public class ReservaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReservaResource
     */
    public ReservaResource() {
    }

    /**
     * Retrieves representation of an instance of co.edu.javeriana.servicio.ReservaResource
     * @return an instance of java.lang.String
     
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ReservaResource
     * @param content representation for the resource
     
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }*/
    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String addReserva(@QueryParam("NUM_ID") BigDecimal NUM_ID,
                            @QueryParam("ORDER_DATE") String ORDER_DATE,
                            @QueryParam("PRICE") BigDecimal PRICE,
                            @QueryParam("STATUS") String STATUS,
                            @QueryParam("COMMENTS") String COMMENTS,
                            @QueryParam("CUST_DOCUMENT_NUMBER") String CUST_DOCUMENT_NUMBER,
                            @QueryParam("CUST_DOCUMENT_TYPE") String CUST_DOCUMENT_TYPE) {
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        try {
            String resultado;
            consultas cons = new consultas();
            resultado = cons.creacionReserva(NUM_ID, ORDER_DATE, PRICE, STATUS, COMMENTS, CUST_DOCUMENT_NUMBER, CUST_DOCUMENT_TYPE);
            return resultado;
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
            return "Error";
        } 
    }
}
