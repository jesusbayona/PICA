/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.objetos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "SALES_ORDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesOrder.findAll", query = "SELECT s FROM SalesOrder s")
    , @NamedQuery(name = "SalesOrder.findById", query = "SELECT s FROM SalesOrder s WHERE s.id = :id")
    , @NamedQuery(name = "SalesOrder.findByOrderDate", query = "SELECT s FROM SalesOrder s WHERE s.orderDate = :orderDate")
    , @NamedQuery(name = "SalesOrder.findByPrice", query = "SELECT s FROM SalesOrder s WHERE s.price = :price")
    , @NamedQuery(name = "SalesOrder.findByStatus", query = "SELECT s FROM SalesOrder s WHERE s.status = :status")
    , @NamedQuery(name = "SalesOrder.findByComments", query = "SELECT s FROM SalesOrder s WHERE s.comments = :comments")
    , @NamedQuery(name = "SalesOrder.findByCustDocumentNumber", query = "SELECT s FROM SalesOrder s WHERE s.custDocumentNumber = :custDocumentNumber")
    , @NamedQuery(name = "SalesOrder.findByCustDocumentType", query = "SELECT s FROM SalesOrder s WHERE s.custDocumentType = :custDocumentType")})
public class SalesOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "ORDER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(name = "PRICE")
    private Long price;
    @Size(max = 4)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 4000)
    @Column(name = "COMMENTS")
    private String comments;
    @Size(max = 20)
    @Column(name = "CUST_DOCUMENT_NUMBER")
    private String custDocumentNumber;
    @Size(max = 4)
    @Column(name = "CUST_DOCUMENT_TYPE")
    private String custDocumentType;

    public SalesOrder() {
    }

    public SalesOrder(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCustDocumentNumber() {
        return custDocumentNumber;
    }

    public void setCustDocumentNumber(String custDocumentNumber) {
        this.custDocumentNumber = custDocumentNumber;
    }

    public String getCustDocumentType() {
        return custDocumentType;
    }

    public void setCustDocumentType(String custDocumentType) {
        this.custDocumentType = custDocumentType;
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
        if (!(object instanceof SalesOrder)) {
            return false;
        }
        SalesOrder other = (SalesOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.javeriana.objetos.SalesOrder[ id=" + id + " ]";
    }
    
}
