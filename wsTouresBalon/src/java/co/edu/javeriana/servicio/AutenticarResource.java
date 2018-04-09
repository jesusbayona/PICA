/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.servicio;

import co.edu.javeriana.objetos.consultas;
import java.sql.SQLException;
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
@Path("autenticar")
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
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String autenticacion(@QueryParam("email") String email,@QueryParam("pass") String pass) throws SQLException {
        //TODO return proper representation object
         String resultado;
            consultas cons = new consultas();
            resultado = cons.autenticacion(email, pass);
            return resultado;
    }
}
