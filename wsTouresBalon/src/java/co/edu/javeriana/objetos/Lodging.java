/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.objetos;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jesus Bayona
 */
@Entity
@Table(name = "LODGING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lodging.findAll", query = "SELECT l FROM Lodging l")
    , @NamedQuery(name = "Lodging.findById", query = "SELECT l FROM Lodging l WHERE l.id = :id")
    , @NamedQuery(name = "Lodging.findByName", query = "SELECT l FROM Lodging l WHERE l.name = :name")
    , @NamedQuery(name = "Lodging.findByCost", query = "SELECT l FROM Lodging l WHERE l.cost = :cost")})
public class Lodging implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Column(name = "COST")
    private Long cost;

    public Lodging() {
    }

    public Lodging(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lodging)) {
            return false;
        }
        Lodging other = (Lodging) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.javeriana.objetos.Lodging[ id=" + id + " ]";
    }
    
}
