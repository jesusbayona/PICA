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
import javax.json.stream.JsonParser;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Param;

/**
 * REST Web Service
 *
 * @author Jesus Bayona
 */
@Path("quote")
@Produces("application/json")
public class QuoteResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of QuoteResource
     */
    public QuoteResource() {
    }

   
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String addCarrito(@QueryParam("productoNum") String productoNum,
                            @QueryParam("productNombre") String productNombre,
                            @QueryParam("precioProduc") String precioProduc,
                            @QueryParam("cantidad") String cantidad,
                            @QueryParam("totalCompra") String totalCompra,                            
                            @QueryParam("status") String status,
                            @QueryParam("idCliente") String idCliente) {
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        Gson gsonBuilder = new GsonBuilder().create();
        try {
            String resultado;
            consultas cons = new consultas();
            resultado = cons.creacionCarrito(productoNum, productNombre, precioProduc, cantidad, totalCompra, status, idCliente);
            
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
            personMap.put("Status", "500");
            personMap.put("respuesta", "Error al consultar productos");
            String jsonFromJavaMap = gsonBuilder.toJson(personMap);
            return jsonFromJavaMap;
        } 
    }
    
    @DELETE
    @Path("eliminarCarrito")
    @Produces({MediaType.APPLICATION_JSON})
    public String emilinarCarrito(@QueryParam("idProducto")String idProducto){
        Gson gsonBuilder = new GsonBuilder().create();
        String resultado;
        try{    
            consultas cons = new consultas();
            resultado = cons.eliminarDelCarrito(idProducto);
            Map personMap = new HashMap();
            personMap.put("code", "200");
            personMap.put("Status", "success");
            personMap.put("respuesta", resultado);
            String jsonFromJavaMap = gsonBuilder.toJson(personMap);
            return jsonFromJavaMap;
        }catch(Exception e){
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
            Map personMap = new HashMap();
            personMap.put("code", "500");
            personMap.put("Status", "fail");
            personMap.put("respuesta", "Error al consultar productos");
            String jsonFromJavaMap = gsonBuilder.toJson(personMap);
            return jsonFromJavaMap;
        }
    }
}
