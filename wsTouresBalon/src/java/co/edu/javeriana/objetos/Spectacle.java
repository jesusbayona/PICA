/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.objetos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "SPECTACLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Spectacle.findAll", query = "SELECT s FROM Spectacle s")
    , @NamedQuery(name = "Spectacle.findById", query = "SELECT s FROM Spectacle s WHERE s.id = :id")
    , @NamedQuery(name = "Spectacle.findByName", query = "SELECT s FROM Spectacle s WHERE s.name = :name")
    , @NamedQuery(name = "Spectacle.findByCost", query = "SELECT s FROM Spectacle s WHERE s.cost = :cost")})
public class Spectacle implements Serializable {

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
    private BigInteger cost;

    public Spectacle() {
    }

    public Spectacle(BigDecimal id) {
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

    public BigInteger getCost() {
        return cost;
    }

    public void setCost(BigInteger cost) {
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
        if (!(object instanceof Spectacle)) {
            return false;
        }
        Spectacle other = (Spectacle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.javeriana.objetos.Spectacle[ id=" + id + " ]";
    }
    
}
