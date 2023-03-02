package edu.gqq.funcinterface;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SupplierTest {

    /*
    IntSupplier int getAsInt();
    DoubleSupplier double getAsDouble();
    LongSupplier long getAsLong();
    BooleanSupplier boolean getAsBoolean();
     */

    private static final Logger logger = LoggerFactory.getLogger(SupplierTest.class);

    public static void main(String[] args) {
        SupplierTest st = new SupplierTest();
        st.testSupplier();
    }

    private void testSupplier() {
        Supplier<Double> supplier = Math::random;
        DoubleSupplier dbsupplier = Math::random;
        // if getDoubleLess100 is NOT static, we can use this way.
        Supplier<Double> supplier1 = this::getDoubleMorethan1000;
        logger.info("supplier.get():" + supplier.get());
        logger.info("dbsupplier.get():" + dbsupplier.getAsDouble());
        logger.info("supplier1.get():" + supplier1.get());
    }

    private double getDoubleMorethan1000() {
        return ThreadLocalRandom.current().nextDouble(1000.0, 9999.0);
    }
}
