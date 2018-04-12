/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.servicio;

import co.edu.javeriana.objetos.consultas;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
@Path("verCarrito")
@Produces({MediaType.APPLICATION_JSON})
public class VerCarritoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VerCarritoResource
     */
    public VerCarritoResource() {
    }
    
     @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String consultarCarrito(@QueryParam("ideCliente") String ideCliente) {
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        String jsonFromJavaArrayList = null;
        Gson gsonBuilder = new GsonBuilder().create();
        consultas cons = new consultas();
        List languagesArrayList = new ArrayList();
        int valor = 0;
        try {
            //producto resultado = new producto();
            languagesArrayList.add(cons.consultarDetalleCarro(ideCliente));
            jsonFromJavaArrayList = gsonBuilder.toJson(languagesArrayList);
            valor = cons.totalCompraCarro(ideCliente);
            
            Map<String, Object> personMap = new HashMap<String, Object>();
            personMap.put("code", "200");
            personMap.put("status", "success");
            personMap.put("totalcompras", valor);
            personMap.put("productos", languagesArrayList);
            String jsonFromJavaMap = gsonBuilder.toJson(personMap);
            return jsonFromJavaMap;
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage());
            Map personMap = new HashMap();
            personMap.put("Status", "401");
            personMap.put("status", "fail");
            personMap.put("respuesta", "Error al consultar productos");
            String jsonFromJavaMap = gsonBuilder.toJson(personMap);
            return jsonFromJavaMap;
        } 
        
    }
    
}
