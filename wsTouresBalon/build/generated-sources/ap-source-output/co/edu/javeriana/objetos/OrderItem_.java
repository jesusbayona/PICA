package co.edu.javeriana.objetos;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-09T01:44:56")
@StaticMetamodel(OrderItem.class)
public class OrderItem_ { 

    public static volatile SingularAttribute<OrderItem, Short> quantity;
    public static volatile SingularAttribute<OrderItem, Long> productId;
    public static volatile SingularAttribute<OrderItem, BigInteger> orderId;
    public static volatile SingularAttribute<OrderItem, Long> price;
    public static volatile SingularAttribute<OrderItem, BigInteger> id;
    public static volatile SingularAttribute<OrderItem, String> productName;
    public static volatile SingularAttribute<OrderItem, String> partnum;

}