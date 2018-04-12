/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.objetos;

import java.math.BigDecimal;

/**
 *
 * @author Jesus Bayona
 */
public class producto {
    private BigDecimal PRODUCTID;
    private String NAME;
    private String SPECTACLE_DATE;
    private String ARRIVAL_DATE;
    private BigDecimal TRANSPORT_TYPE;
    private BigDecimal SPECTACLE_TYPE;
    private BigDecimal LODGING_TYPE;
    private String DESCRIPTION;
    private String CODE;
    private byte[] IMAGE_REF;
    private BigDecimal SOURCE_CITY;
    private BigDecimal TARGET_CITY;
    private String DEPARTURE_DATE;
    private String VALORVENTA;
    private byte[] THUMBNAIL;
    
    public String getVALORVENTA() {
        return VALORVENTA;
    }

    public void setVALORVENTA(String VALORVENTA) {
        this.VALORVENTA = VALORVENTA;
    }

    public byte[] getTHUMBNAIL() {
        return THUMBNAIL;
    }

    public void setTHUMBNAIL(byte[] THUMBNAIL) {
        this.THUMBNAIL = THUMBNAIL;
    }
    
    

    public BigDecimal getPRODUCTID() {
        return PRODUCTID;
    }

    public void setPRODUCTID(BigDecimal PRODUCTID) {
        this.PRODUCTID = PRODUCTID;
    }

    
    public void setDEPARTURE_DATE(String DEPARTURE_DATE) {
        this.DEPARTURE_DATE = DEPARTURE_DATE;
    }

    public String getDEPARTURE_DATE() {
        return DEPARTURE_DATE;
    }

    public String getNAME() {
        return NAME;
    }

    public String getSPECTACLE_DATE() {
        return SPECTACLE_DATE;
    }

    public String getARRIVAL_DATE() {
        return ARRIVAL_DATE;
    }

    public BigDecimal getTRANSPORT_TYPE() {
        return TRANSPORT_TYPE;
    }

    public BigDecimal getSPECTACLE_TYPE() {
        return SPECTACLE_TYPE;
    }

    public BigDecimal getLODGING_TYPE() {
        return LODGING_TYPE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public String getCODE() {
        return CODE;
    }

    public byte[] getIMAGE_REF() {
        return IMAGE_REF;
    }

    public BigDecimal getSOURCE_CITY() {
        return SOURCE_CITY;
    }

    public BigDecimal getTARGET_CITY() {
        return TARGET_CITY;
    }


    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setSPECTACLE_DATE(String SPECTACLE_DATE) {
        this.SPECTACLE_DATE = SPECTACLE_DATE;
    }

    public void setARRIVAL_DATE(String ARRIVAL_DATE) {
        this.ARRIVAL_DATE = ARRIVAL_DATE;
    }

    public void setTRANSPORT_TYPE(BigDecimal TRANSPORT_TYPE) {
        this.TRANSPORT_TYPE = TRANSPORT_TYPE;
    }

    public void setSPECTACLE_TYPE(BigDecimal SPECTACLE_TYPE) {
        this.SPECTACLE_TYPE = SPECTACLE_TYPE;
    }

    public void setLODGING_TYPE(BigDecimal LODGING_TYPE) {
        this.LODGING_TYPE = LODGING_TYPE;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public void setIMAGE_REF(byte[] IMAGE_REF) {
        this.IMAGE_REF = IMAGE_REF;
    }

    public void setSOURCE_CITY(BigDecimal SOURCE_CITY) {
        this.SOURCE_CITY = SOURCE_CITY;
    }

    public void setTARGET_CITY(BigDecimal TARGET_CITY) {
        this.TARGET_CITY = TARGET_CITY;
    }
    
    
}
