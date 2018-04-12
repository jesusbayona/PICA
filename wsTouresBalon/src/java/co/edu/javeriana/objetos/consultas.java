/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.objetos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Jesus Bayona
 */
public class consultas {

    public String creacionCarrito(String productoNum,
            String productNombre,
            String precioProduc,
            String cantidad,
            String totalCompra,
            String STATUS,
            String idCliente) throws SQLException {
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        int incremental = 0;
        int existe = 0;
        int respuesta = 0;
        int suma = 0;
        try {
            String comentarios = null;
            String Valor = "Se guardo correctamente la información con el numero: ";
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, usuario, contrasena);
            Statement stmt = con.createStatement();

            //CONSULTO SI TIENE UN CARRO ACTIVO
            String existencia = "select COUNT(*) from SALES_QUOTE SQ "
                    + " WHERE SQ.IDCUSTOMER =" + idCliente
                    + " AND SQ.STATUS= 'A'";
            ResultSet rs = stmt.executeQuery(existencia);
            while (rs.next()) {
                existe = rs.getInt(1);
            }

            if (existe > 0) {
                //CONSULTO NUM_ID PARA ENIVIARLO A CREAR EN LA TABLA ITEMS
                String consultaID = "select SQ.NUM_ID from SALES_QUOTE SQ "
                        + " WHERE SQ.IDCUSTOMER =" + idCliente
                        + " AND SQ.STATUS= 'A'";
                ResultSet consId = stmt.executeQuery(consultaID);
                while (consId.next()) {
                    existe = consId.getInt(1);
                }
                respuesta = creacionQuoteItems(productoNum, productNombre, "", precioProduc, cantidad, existe);
                consId.close();

                String consultaPrecios = "select  "
                        + "  sum(ee.COST +ll.COST +tt.COST) valor "
                        + " from PRODUCT po, SPECTACLE ee,LODGING ll, TRANSPORT tt, QUOTE_ITEMS oi "
                        + " where  ee.id = po.SPECTACLE_TYPE "
                        + " and ll.ID = po.LODGING_TYPE "
                        + " and tt.ID = po.TRANSPORT_TYPE  "
                        + " and  po.PRODUCTOID = oi.PRODUCT_ID "
                        + " and oi.quote_id =" + existe;
                ResultSet consPre = stmt.executeQuery(consultaPrecios);

                while (consPre.next()) {
                    suma = consPre.getInt(1);
                }
                consPre.close();
                try {
                    PreparedStatement ps = con.prepareStatement("UPDATE SALES_QUOTE SET TOTALCOMPRA = ? WHERE NUM_ID = ?");
                    ps.setInt(1,suma);
                    ps.setInt(2,existe);
                    ps.executeUpdate();
                    ps.close();
                } catch (SQLException e) {

                    System.out.println(e.getMessage());

                }

            } else {
                String secuencia = "SELECT SEQ_SALES_QUOTE.NEXTVAL FROM DUAL";

                ResultSet rs1 = stmt.executeQuery(secuencia);

                while (rs1.next()) {
                    incremental = rs1.getInt(1);
                }

                String inserta = "INSERT INTO SALES_QUOTE (NUM_ID,ORDER_DATE,TOTALCOMPRA,STATUS,COMMENTS,IDCUSTOMER)"
                        + "VALUES (" + incremental + ",TO_DATE(SYSDATE,'DD/MM/YYYY')," + totalCompra + ",'" + STATUS + "','" + comentarios + "','" + idCliente + "')";
                stmt.executeUpdate(inserta);
                respuesta = creacionQuoteItems(productoNum, productNombre, "", precioProduc, cantidad, incremental);
                rs1.close();
            }

            rs.close();
            stmt.close();
            con.close();

            if (respuesta > 0) {
                return Valor + respuesta;
            } else {
                return "Se genero un error al guardar el Carro de compras";
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return "Se genero un error al guardar el Carro de compras";
        }
    }

    public int creacionQuoteItems(String PRODUCT_ID,
            String PRODUCT_NAME,
            String PARTNUM,
            String PRICE,
            String QUANTITY,
            int QUOTE_ID) throws SQLException {
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]

        int incremento = 0;
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, usuario, contrasena);

            String secuencia = "SELECT SEQ_SALES_QUOTE.NEXTVAL FROM DUAL";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(secuencia);

            while (rs.next()) {
                incremento = rs.getInt(1);
            }

            String insert = "INSERT INTO QUOTE_ITEMS (NUM_ID,PRODUCT_ID,PRODUCT_NAME,PARTNUM,PRICE,QUANTITY,QUOTE_ID)"
                    + "VALUES (" + incremento + "," + PRODUCT_ID + ",'" + PRODUCT_NAME + "','" + PARTNUM + "'," + PRICE + "," + QUANTITY + "," + QUOTE_ID + ")";
            Statement inserto = con.createStatement();
            inserto.executeUpdate(insert);
            stmt.close();
            inserto.close();
            con.close();
            return incremento;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return 0;
        }
    }

    public String creacionReserva(String ideCliente) throws SQLException {
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        int validacion = 0;
        int incremental = 0;
        String comentarios = null;
        int respuesta = 0;
        int existe = 0;
        String tipoIdeCliente = null;
        String numIdeCliente = null;
        String numeroCarrito = null;
        Connection con = null;
        try {
            String Valor = "Se guardo exitosamente la reserva con el numero: ";
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, usuario, contrasena);
            Statement stmt = con.createStatement();
            
            //CONSULTO PARA IDENTIFICAR SI EXISTE EL USUARIO EN LA TABLA
            String consultaID = "select SQ.NUM_ID, CU.TIPO_IDENTIFICIACION, CU.NUM_IDENIFICACION " +
                                " from SALES_QUOTE SQ, CUSTOMER CU " +
                                " WHERE SQ.STATUS= 'A' " +
                                " AND CU.ID = SQ.IDCUSTOMER " +
                                " AND SQ.IDCUSTOMER = "+ ideCliente;
            ResultSet rs = stmt.executeQuery(consultaID);
            while (rs.next()) {
                tipoIdeCliente = rs.getString("TIPO_IDENTIFICIACION");
                numIdeCliente = rs.getString("NUM_IDENIFICACION");
                numeroCarrito = rs.getString("NUM_ID");
            }
            

            //CONSULTO PARA IDENTIFICAR SI EXISTE EL USUARIO EN LA TABLA
            String existencia = "SELECT COUNT(1) FROM SALES_ORDER SO "
                    + " WHERE SO.CUST_DOCUMENT_TYPE = '" + tipoIdeCliente + "'"
                    + " AND SO.CUST_DOCUMENT_NUMBER = '" + numIdeCliente + "'"
                    + " AND SO.STATUS = 'A'";
            ResultSet rs2 = stmt.executeQuery(existencia);
            while (rs2.next()) {
                validacion = rs2.getInt(1);
            }

            if (validacion > 0) {
                //CONSULTO NUM_ID PARA ENIVIARLO A CREAR EN LA TABLA ITEMS
                String consultaID1 = "SELECT SO.SALES_ID FROM SALES_ORDER SO "
                        + " WHERE SO.CUST_DOCUMENT_TYPE = '" + tipoIdeCliente + "'"
                        + " AND SO.CUST_DOCUMENT_NUMBER = '" + numIdeCliente + "'"
                        + " AND SO.STATUS = 'A'";
                ResultSet consId = stmt.executeQuery(consultaID1);
                while (consId.next()) {
                    existe = consId.getInt(1);
                }
                
                //CONSULTO itmes quotes PARA ENIVIARLO A CREAR EN LA TABLA ITEMS
                String consultaID2 = " SELECT * FROM QUOTE_ITEMS OI " +
                                     " WHERE OI.QUOTE_ID = "+ numeroCarrito;
                ResultSet consId1 = stmt.executeQuery(consultaID2);
                while (consId1.next()) {
                    respuesta = creacionItemReserva(consId1.getString("PRODUCT_ID"), consId1.getString("PRODUCT_NAME"),
                            consId1.getString("PARTNUM"), consId1.getString("PRICE"), consId1.getString("QUANTITY"), existe);
                }
                consId.close();
                consId1.close();
            } else {
                String secuencia = "SELECT SEQ_SALES_ORDER.NEXTVAL FROM DUAL";

                ResultSet rs1 = stmt.executeQuery(secuencia);

                while (rs1.next()) {
                    incremental = rs1.getInt(1);
                }

                String insert = "INSERT INTO SALES_ORDER (SALES_ID,ORDER_DATE,TOTAL,STATUS,COMMENTS,CUST_DOCUMENT_NUMBER, CUST_DOCUMENT_TYPE)"
                        + "VALUES (" + incremental + ",TO_DATE(SYSDATE,'DD/MM/YYYY'),'','P','" + comentarios + "','" + numIdeCliente + "','" + tipoIdeCliente + "')";

                stmt.executeUpdate(insert);

                //CONSULTO itmes quotes PARA ENIVIARLO A CREAR EN LA TABLA ITEMS
                String consultaID2 = " SELECT * FROM QUOTE_ITEMS OI " +
                                     " WHERE OI.QUOTE_ID = "+ numeroCarrito;
                ResultSet consId1 = stmt.executeQuery(consultaID2);
                while (consId1.next()) {
                    respuesta = creacionItemReserva(consId1.getString("PRODUCT_ID"), consId1.getString("PRODUCT_NAME"),
                            consId1.getString("PARTNUM"), consId1.getString("PRICE"), consId1.getString("QUANTITY"), incremental);
                }
                consId1.close();
                stmt.close();
                rs.close();
            }

            if (respuesta > 0) {
                PreparedStatement ps = con.prepareStatement("UPDATE SALES_QUOTE SET STATUS = 'I' WHERE NUM_ID = ?");
                ps.setString(1,numeroCarrito);
                ps.executeUpdate();
                ps.close();
                con.close();
                return Valor + respuesta;
            } else {
                return "Se genero un error al guardar la reserva";
            }            
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return "Se genero un error al guardar la reserva";
        }
        
    }

    public int creacionItemReserva(String PRODUCT_ID,
            String PRODUCT_NAME,
            String PARTNUM,
            String PRICE,
            String QUANTITY,
            int SALES_ID) throws SQLException {
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        int incremental = 0;
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, usuario, contrasena);
            Statement stmt = con.createStatement();

            //CONSULTO PARA IDENTIFICAR SI EXISTE EL USUARIO EN LA TABLA
            String secuencia = "SELECT SEQ_SALES_ORDER_ITEM.NEXTVAL FROM DUAL";

            ResultSet rs1 = stmt.executeQuery(secuencia);

            while (rs1.next()) {
                incremental = rs1.getInt(1);
            }

            String insert = "INSERT INTO SALES_ORDER_ITEM (NUM_ID,PRODUCT_ID,PRODUCT_NAME,PARTNUM,PRECIO,QUANTITY,SALES_ORDER_ID)"
                    + "VALUES (" + incremental + "," + PRODUCT_ID + ",'" + PRODUCT_NAME + "','" + PARTNUM + "'," + PRICE + "," + QUANTITY + "," + SALES_ID + ")";

            stmt.executeUpdate(insert);

            rs1.close();
            stmt.close();
            con.close();
            return incremental;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return 0;
        }
    }

    public List<Cliente> autenticacion(String email,
            String pass) throws SQLException {
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        List<Cliente> respuesta = new ArrayList<Cliente>();
        try {

            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, usuario, contrasena);
            String insert = "SELECT C.FIRST_NAME, C.LAST_NAME, C.ID, C.TIPO_IDENTIFICIACION, C.NUM_IDENIFICACION, C.EMAIL FROM CUSTOMER C "
                    + "WHERE C.EMAIL ='" + email + "' AND  C.PASSWORD ='" + pass + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(insert);
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setApellidos(rs.getString("LAST_NAME"));
                cliente.setNombres(rs.getString("FIRST_NAME"));
                cliente.setIdCliente(rs.getString("ID"));
                cliente.setTipoIdentificacion(rs.getString("TIPO_IDENTIFICIACION"));
                cliente.setNumIdentificacion(rs.getString("NUM_IDENIFICACION"));
                cliente.setEmail(rs.getString("EMAIL"));

                respuesta.add(cliente);
            }
            stmt.close();
            rs.close();
            con.close();
            return respuesta;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            Cliente cliente = new Cliente();
            cliente.setError("Usuario y/o contraseña incorrecto");
            respuesta.add(cliente);
            return respuesta;
        }

    }

    public List<producto> allProductos() throws SQLException {
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        List<producto> respuesta = new ArrayList<producto>();
        try {
            byte[] emptyArray = new byte[0];
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, usuario, contrasena);
            String consulta = "SELECT P.PRODUCTOID, P.NAME, P.DESCRIPTION, P.VALORVENTA, P.THUMBNAIL FROM PRODUCT P";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                producto prod = new producto();
                prod.setPRODUCTID(rs.getBigDecimal("PRODUCTOID"));
                prod.setNAME(rs.getString("NAME"));
                prod.setDESCRIPTION(rs.getString("DESCRIPTION"));
                prod.setVALORVENTA(rs.getString("VALORVENTA"));
                //prod.setIMAGE_REF(rs.getString("IMAGE_REF"));
                //prod.setTHUMBNAIL(rs.getBytes("THUMBNAIL"));         
                prod.setTHUMBNAIL(emptyArray);

                respuesta.add(prod);
            }

            con.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        return respuesta;
    }

    public List<Carrito> consultarDetalleCarro(String idCliente) {
        List<Carrito> detalle = new ArrayList<Carrito>();
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, usuario, contrasena);
            String consulta = "select "
                    + "  po.NAME nombreproducto, "
                    + "  po.DESCRIPTION descripcionproducto, "
                    + "  po.THUMBNAIL imgminiproducto, "
                    + "  po.PRODUCTOID productoid, "
                    + "  po.CODE codigoproducto, "
                    + "  to_char(PO.DEPARTURE_DATE) fechasalida, "
                    + "  to_char(PO.ARRIVAL_DATE) fechallegada, "
                    + "  ee.NAME nombreespectaculo, "
                    + "  ee.COST valorevento, "
                    + " ll.NAME nombrehospedaje "
                    + " ,ll.COST valorhospedaje, "
                    + " tt.NAME nombretransporte, "
                    + " tt.COST valortransporte, "
                    +"  sq.totalcompra ,"
                    +" ii.quantity cantidad,"
                    +" ii.price precioItem "
                    + " from sales_quote sq ,QUOTE_ITEMS ii,PRODUCT po, SPECTACLE ee,LODGING ll, TRANSPORT tt "
                    + " where sq.NUM_ID = ii.QUOTE_ID  "
                    + " and ii.PRODUCT_ID = po.PRODUCTOID "
                    + " and ee.id = po.SPECTACLE_TYPE "
                    + " and ll.ID = po.LODGING_TYPE "
                    + " and tt.ID = po.TRANSPORT_TYPE "
                    + " and sq.status = 'A'"
                    + " and sq.IDCUSTOMER = " + idCliente;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                Carrito carro = new Carrito();
                carro.setNombreproducto(rs.getString("nombreproducto"));
                carro.setDescripcionproducto(rs.getString("descripcionproducto"));
                //carro.setImgminiproducto(rs.getString("imgminiproducto"));
                carro.setProductoid(rs.getString("productoid"));
                carro.setCodigoproducto(rs.getString("codigoproducto"));
                carro.setFechasalida(rs.getString("fechasalida"));
                carro.setFechallegada(rs.getString("fechallegada"));
                carro.setNombreespectaculo(rs.getString("nombreespectaculo"));
                carro.setValorevento(rs.getString("valorevento"));
                carro.setNombrehospedaje(rs.getString("nombrehospedaje"));
                carro.setValorhospedaje(rs.getString("valorhospedaje"));
                carro.setNombretransporte(rs.getString("nombretransporte"));
                carro.setValortransporte(rs.getString("valortransporte"));
                carro.setTotalCompra(rs.getString("totalcompra"));
                carro.setCantidadItem(rs.getString("cantidad"));
                carro.setPrecioItem(rs.getString("precioItem"));
                detalle.add(carro);
            }
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        return detalle;
    }
    
    public int totalCompraCarro(String idCliente){
        int valor = 0;
        try{
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, usuario, contrasena);
            
            String consulta = " SELECT SQ.TOTALCOMPRA FROM SALES_QUOTE SQ " +
                              " WHERE SQ.STATUS = 'A' " +
                              " AND SQ.IDCUSTOMER = " + idCliente;
                              
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                valor = rs.getInt(1);
            }
            return valor;
        }catch(Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return 0;
        }        
    }

    public List<Carrito> verDetalleProducto(String idProducto) {
        List<Carrito> detalleProducto = new ArrayList<Carrito>();
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, usuario, contrasena);
            String consulta = " select "
                    + "  po.NAME nombreproducto, "
                    + "  po.DESCRIPTION descripcionproducto, "
                    + "  po.THUMBNAIL imgminiproducto, "
                    + "  po.PRODUCTOID productoid, "
                    + "  po.CODE codigoproducto, "
                    + "  to_char(PO.DEPARTURE_DATE) fechasalida, "
                    + "  to_char(PO.ARRIVAL_DATE) fechallegada, "
                    + "  ee.NAME nombreespectaculo, "
                    + "  ee.COST valorevento, "
                    + " ll.NAME nombrehospedaje "
                    + " ,ll.COST valorhospedaje, "
                    + " tt.NAME nombretransporte, "
                    + " tt.COST valortransporte "
                    + " from PRODUCT po, SPECTACLE ee,LODGING ll, TRANSPORT tt "
                    + " where ee.id = po.SPECTACLE_TYPE "
                    + " and ll.ID = po.LODGING_TYPE "
                    + " and tt.ID = po.TRANSPORT_TYPE "
                    + " and po.PRODUCTOID = " + idProducto;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                Carrito carro = new Carrito();
                carro.setNombreproducto(rs.getString("nombreproducto"));
                carro.setDescripcionproducto(rs.getString("descripcionproducto"));
                //carro.setImgminiproducto(rs.getString("imgminiproducto"));
                carro.setProductoid(rs.getString("productoid"));
                carro.setCodigoproducto(rs.getString("codigoproducto"));
                carro.setFechasalida(rs.getString("fechasalida"));
                carro.setFechallegada(rs.getString("fechallegada"));
                carro.setNombreespectaculo(rs.getString("nombreespectaculo"));
                carro.setValorevento(rs.getString("valorevento"));
                carro.setNombrehospedaje(rs.getString("nombrehospedaje"));
                carro.setValorhospedaje(rs.getString("valorhospedaje"));
                carro.setNombretransporte(rs.getString("nombretransporte"));
                carro.setValortransporte(rs.getString("valortransporte"));

                detalleProducto.add(carro);
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return detalleProducto;
    }

    public String eliminarDelCarrito(String idProducto) {
        String resultado = null;
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, usuario, contrasena);
            String consulta = " DELETE FROM QUOTE_ITEMS OI "
                    + "  WHERE OI.NUM_ID = " + idProducto;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            stmt.close();
            con.close();
            resultado = "Eliminación del carrito exitoso";
            return resultado;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            resultado = "Eliminación del carrito fallido";
            return resultado;
        }
    }

}
