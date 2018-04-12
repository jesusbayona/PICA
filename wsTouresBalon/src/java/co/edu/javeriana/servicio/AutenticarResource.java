/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.servicio;

import co.edu.javeriana.objetos.consultas;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * REST Web Service
 *
 * @author Jesus Bayona
 */
@Path("autenticar")
@Produces("application/json")
public class AutenticarResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AutenticarResource
     */
    public AutenticarResource() {
    }

    /**
     * Retrieves representation of an instance of co.edu.javeriana.servicio.AutenticarResource
     * @return an instance of java.lang.String
     
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AutenticarResource
     * @param content representation for the resource
     
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }*/
     
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String autenticacion(@QueryParam("email") String email,@QueryParam("pass") String pass) throws SQLException {
        //TODO return proper representation object
        List languagesArrayList = new ArrayList();
        consultas cons = new consultas();
        Gson gsonBuilder = new GsonBuilder().create();
        
        try{
            languagesArrayList.add(cons.autenticacion(email, pass)); 
            
            Map<String, Object> personMap = new HashMap<String, Object>();
            personMap.put("code", "200");
            personMap.put("status", "success");
            personMap.put("autenticacion", languagesArrayList);
            String jsonFromJavaMap = gsonBuilder.toJson(personMap);
            return jsonFromJavaMap;
        }catch(Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage());
            Map personMap = new HashMap();
            personMap.put("code", "500");
            personMap.put("status", "fall");
            personMap.put("respuesta", "Usuario y/o contraseña erroneo");
            String jsonFromJavaMap = gsonBuilder.toJson(personMap);
            return jsonFromJavaMap;
        } 
        
         
    }
}
