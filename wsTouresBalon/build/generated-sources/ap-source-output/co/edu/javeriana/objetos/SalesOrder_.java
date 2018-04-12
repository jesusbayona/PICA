package co.edu.javeriana.objetos;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-11T15:59:47")
@StaticMetamodel(SalesOrder.class)
public class SalesOrder_ { 

    public static volatile SingularAttribute<SalesOrder, String> comments;
    public static volatile SingularAttribute<SalesOrder, String> custDocumentType;
    public static volatile SingularAttribute<SalesOrder, Long> price;
    public static volatile SingularAttribute<SalesOrder, BigDecimal> id;
    public static volatile SingularAttribute<SalesOrder, Date> orderDate;
    public static volatile SingularAttribute<SalesOrder, String> status;
    public static volatile SingularAttribute<SalesOrder, String> custDocumentNumber;

}