/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.objetos;

/**
 *
 * @author Jesus Bayona
 */
public class Cliente {
   private String apellidos; 
   private String nombres; 
   private String idCliente;
   private String tipoIdentificacion;
   private String numIdentificacion;
   private String email;
   private String error;

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public String getApellidos() {
        return apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public String getEmail() {
        return email;
    }

    public String getError() {
        return error;
    }
    
    
}
