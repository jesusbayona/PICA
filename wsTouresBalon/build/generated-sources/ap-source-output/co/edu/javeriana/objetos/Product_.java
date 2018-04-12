package co.edu.javeriana.objetos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-11T15:59:47")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, String> code;
    public static volatile SingularAttribute<Product, BigInteger> targetCity;
    public static volatile SingularAttribute<Product, BigInteger> lodgingType;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Date> spectacleDate;
    public static volatile SingularAttribute<Product, Date> arrivalDate;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, BigInteger> transportType;
    public static volatile SingularAttribute<Product, BigDecimal> id;
    public static volatile SingularAttribute<Product, Date> departureDate;
    public static volatile SingularAttribute<Product, BigInteger> spectacleType;
    public static volatile SingularAttribute<Product, byte[]> imageRef;
    public static volatile SingularAttribute<Product, BigInteger> sourceCity;

}