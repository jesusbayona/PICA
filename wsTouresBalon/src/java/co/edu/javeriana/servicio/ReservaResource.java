/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.servicio;

import co.edu.javeriana.objetos.consultas;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
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
@Produces("application/json")
public class ReservaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReservaResource
     */
    public ReservaResource() {
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String addReserva(@QueryParam("ideCliente") String ideCliente) {
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        Gson gsonBuilder = new GsonBuilder().create();
        try {
            String resultado;
            consultas cons = new consultas();
            resultado = cons.creacionReserva(ideCliente);
            // Convert Java Map into JSON 
            Map personMap = new HashMap();
            personMap.put("code", "200");
            personMap.put("status", "success");
            personMap.put("respuesta", resultado);
            String jsonFromJavaMap = gsonBuilder.toJson(personMap);
            
            return jsonFromJavaMap;
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
            Map personMap = new HashMap();
            personMap.put("code", "500");
            personMap.put("Status", "fail");
            personMap.put("respuesta", "Error al realizar la reserva");
            String jsonFromJavaMap = gsonBuilder.toJson(personMap);
            return jsonFromJavaMap;
        } 
    }
}
