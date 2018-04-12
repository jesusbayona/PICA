/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.servicio;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Jesus Bayona
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.javeriana.servicio.AddressFacadeREST.class);
        resources.add(co.edu.javeriana.servicio.AutenticarResource.class);
        resources.add(co.edu.javeriana.servicio.CityFacadeREST.class);
        resources.add(co.edu.javeriana.servicio.CountryFacadeREST.class);
        resources.add(co.edu.javeriana.servicio.CustomerAddressFacadeREST.class);
        resources.add(co.edu.javeriana.servicio.CustomerFacadeREST.class);
        resources.add(co.edu.javeriana.servicio.DetalleProductoResource.class);
        resources.add(co.edu.javeriana.servicio.LodgingFacadeREST.class);
        resources.add(co.edu.javeriana.servicio.OrderItemFacadeREST.class);
        resources.add(co.edu.javeriana.servicio.ProductFacadeREST.class);
        resources.add(co.edu.javeriana.servicio.ProductosResource.class);
        resources.add(co.edu.javeriana.servicio.QuoteResource.class);
        resources.add(co.edu.javeriana.servicio.ReservaResource.class);
        resources.add(co.edu.javeriana.servicio.SalesOrderFacadeREST.class);
        resources.add(co.edu.javeriana.servicio.SpectacleFacadeREST.class);
        resources.add(co.edu.javeriana.servicio.TransportFacadeREST.class);
        resources.add(co.edu.javeriana.servicio.VerCarritoResource.class);
    }
    
}
