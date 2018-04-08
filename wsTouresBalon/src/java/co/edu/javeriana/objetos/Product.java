/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.objetos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jesus Bayona
 */
@Entity
@Table(name = "PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id")
    , @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name")
    , @NamedQuery(name = "Product.findBySpectacleDate", query = "SELECT p FROM Product p WHERE p.spectacleDate = :spectacleDate")
    , @NamedQuery(name = "Product.findByArrivalDate", query = "SELECT p FROM Product p WHERE p.arrivalDate = :arrivalDate")
    , @NamedQuery(name = "Product.findByDepartureDate", query = "SELECT p FROM Product p WHERE p.departureDate = :departureDate")
    , @NamedQuery(name = "Product.findByTransportType", query = "SELECT p FROM Product p WHERE p.transportType = :transportType")
    , @NamedQuery(name = "Product.findBySpectacleType", query = "SELECT p FROM Product p WHERE p.spectacleType = :spectacleType")
    , @NamedQuery(name = "Product.findByLodgingType", query = "SELECT p FROM Product p WHERE p.lodgingType = :lodgingType")
    , @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description")
    , @NamedQuery(name = "Product.findByCode", query = "SELECT p FROM Product p WHERE p.code = :code")
    , @NamedQuery(name = "Product.findBySourceCity", query = "SELECT p FROM Product p WHERE p.sourceCity = :sourceCity")
    , @NamedQuery(name = "Product.findByTargetCity", query = "SELECT p FROM Product p WHERE p.targetCity = :targetCity")})
public class Product implements Serializable {

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
    @Column(name = "SPECTACLE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date spectacleDate;
    @Column(name = "ARRIVAL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDate;
    @Column(name = "DEPARTURE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDate;
    @Column(name = "TRANSPORT_TYPE")
    private BigInteger transportType;
    @Column(name = "SPECTACLE_TYPE")
    private BigInteger spectacleType;
    @Column(name = "LODGING_TYPE")
    private BigInteger lodgingType;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 20)
    @Column(name = "CODE")
    private String code;
    @Lob
    @Column(name = "IMAGE_REF")
    //private Serializable imageRef;
    private byte[] imageRef;
    @Column(name = "SOURCE_CITY")
    private BigInteger sourceCity;
    @Column(name = "TARGET_CITY")
    private BigInteger targetCity;

    public Product() {
    }

    public Product(BigDecimal id) {
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

    public Date getSpectacleDate() {
        return spectacleDate;
    }

    public void setSpectacleDate(Date spectacleDate) {
        this.spectacleDate = spectacleDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public BigInteger getTransportType() {
        return transportType;
    }

    public void setTransportType(BigInteger transportType) {
        this.transportType = transportType;
    }

    public BigInteger getSpectacleType() {
        return spectacleType;
    }

    public void setSpectacleType(BigInteger spectacleType) {
        this.spectacleType = spectacleType;
    }

    public BigInteger getLodgingType() {
        return lodgingType;
    }

    public void setLodgingType(BigInteger lodgingType) {
        this.lodgingType = lodgingType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getImageRef() {
        return imageRef;
    }

    public void setImageRef(byte[] imageRef) {
        this.imageRef = imageRef;
    }

    public BigInteger getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(BigInteger sourceCity) {
        this.sourceCity = sourceCity;
    }

    public BigInteger getTargetCity() {
        return targetCity;
    }

    public void setTargetCity(BigInteger targetCity) {
        this.targetCity = targetCity;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.javeriana.objetos.Product[ id=" + id + " ]";
    }
    
}
